package furhatos.app.furgui

import furhatos.app.furgui.lecture.*
import furhatos.event.senses.SenseSkillGUIConnected
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhatos.records.Location
import furhatos.records.Record
import furhatos.skills.HostedGUI

// Our GUI declaration
//val GUI = HostedGUI("ExampleGUI", "assets/exampleGui", PORT)
val GUI = HostedGUI("TutorGUI", "assets/TutorGui", PORT)
val VARIABLE_SET = "VariableSet"
val CLICK_BUTTON = "ClickButton"
val MESSAGE = "Message"
val NOTIFICATION = "Notification"

// Starting state, before our GUI has connected.
val NoGUI: State = state(null) {

    onEvent<SenseSkillGUIConnected> {
        println("NoGUI - SenseSkillGUIConnected")
        goto(GUIConnected)
    }

}

/*
    Here we know our GUI has connected. Since the user might close down the GUI and then reopen
    again, we inherit our handler from the NoGUI state. An edge case might be that a second GUI
    is opened, but this is not accounted for here.

 */
val GUIConnected = state(NoGUI) {
    onEntry {
        // Pass data to GUI
        send(DataDelivery(buttons = buttons, inputFields = inputFieldData.keys.toList()))

        println("GUIConnected onEntry. Sending buttons" +buttons+ " Sending inputFields= " +inputFieldData.keys.toList())

        //val text = ("Hello, My name is Alysha Sunil. I am your Physics instructor. Hope you will enjoy this session. " +
        //        "Before we start, could you please submit your registration number?. " +
        //        "Click the Register button on the left side of this page")

        val text = ("Hello")
        furhat.say(text)
        send(SPEECH_DONE)
    }

    // Users clicked any of our buttons
    onEvent(CLICK_BUTTON) {

        val data = it.get("data")
        var text = ""
        if (data == "Pre-Test") {
            val text1 = ("Welcome! This is the initial test, which will check your knowledge of the subject. Best of Luck")
            furhat.say(text1)
            //call(PreTest)
        } else if (data == "Lecture") {
            call(Lecture)
        } else if (data == "Quiz") {
            call(Quiz)
        } else if (data == "Register") {
            text = ("Please enter the 3 digit registration number and then click the save button")
        }else {
            // Directly respond with the value we get from the event, with a fallback
            furhat.say("You pressed ${it.get("data") ?: "something I'm not aware of"}")
        }
        furhat.say(text)

        // Let the GUI know we're done speaking, to unlock buttons
        send(SPEECH_DONE)

        println("GUIConnected onEvent Click Button $data")
    }

    // Users saved some input
    onEvent(VARIABLE_SET) {
        // Get data from event
        val data = it.get("data") as Record
        val variable = data.getString("variable")
        val value = data.getString("value")

        // Get answer depending on what variable we changed and what the new value is, and speak it out
        val answer = inputFieldData[variable]?.invoke(value)
        furhat.say(answer ?: "Something went wrong")

        // Registering the user
        users.current.registrationId = value

        //val instruction = "Please take the test by clicking the button Pre-Test"
        //furhat.say(instruction)

        // Let the GUI know we're done speaking, to unlock buttons
        send(SPEECH_DONE)
    }

    // Notification from GUI to Furhat
    onEvent(MESSAGE) {

        val data = it.get("data").toString()
        furhat.say(data ?: "Something went wrong")

        // Let the GUI know we're done speaking, to unlock buttons
        send(SPEECH_DONE)

        println("Message Received from GUI")
    }

    // Notification from GUI to Furhat
    onEvent(NOTIFICATION) {

        // Get data from event
        val data = it.get("data") as Record
        val eventType = data.getString("event_type")
        val message = data.getString("message")

        //if (eventType == "PRETESTSTARTED") {
        //    call(PreTest)
        //}

        furhat.say(message ?: "Something went wrong")

        // Let the GUI know we're done speaking, to unlock buttons
        send(SPEECH_DONE)

        println("Message Received from GUI")
    }


}

val PreTest = state {

    onEntry {
        println("PreTest state - start")
        val text = ("Best of Luck")
        furhat.say(text)

        call(PreTestQuestion)
        //println("PreTest state - after 1st question")

    }

    // Notification from GUI to Furhat
    onEvent(NOTIFICATION) {
        val data = it.get("data").toString()
        //println("PreTest state - Next Question -  Question No : $data")

        // Hardcoded value of 6 to exit from this loop
        if (data?.toInt() > 5) {
            //println("Terminate from PreTest state")
            terminate()
        } else {
            // Next question notification
            //println("PreTest state - Go to next question")
            call(PreTestQuestion)
            //println("PreTest state - after question 2 onwards")

        }
    }

}


val PreTestQuestion = state(Interaction) {

    var counter = 0

    onEntry {
        println("PreTestQuestion state")
    }

    onTime(delay=20000) {
        counter = 1
        furhat.ask("Do you need any help?", timeout = 3000)
    }

    onTime(delay=40000) {
        counter = 2
        furhat.ask("Do you need any help?", timeout = 3000)
    }

    onTime(delay=60000) {
        counter = 3
        furhat.ask("Do you need any help?", timeout = 3000)
    }

    onResponse<No> {
        //println("PreTestQuestion state - NO - Counter: $counter")
        furhat.say("Okay! Help No ")
    }

    onResponse<Yes> {
        //println("PreTestQuestion state - YES  - Counter: $counter")
        send(HELPTEXT)
        furhat.say("Check your help box to see the hints")
    }

    onNoResponse { // Catches silence
        //println("PreTestQuestion state - NO Response  - Counter: $counter")
        furhat.say("I didn't hear anything")
    }

    // Notification from GUI to Furhat
    onEvent(NOTIFICATION) {
        //println("PreTestQuestion state - Submit Answer -  Counter: $counter")
        val data = it.get("data").toString()
        //println("PreTestQuestion state - Submit Answer -  Question No : $data")
        // Terminate when user submits the answer
        terminate()
    }

}

val Quiz = state {

    onEntry {
        println("Quiz - start")

        val textq1 = utterance {
            +"Welcome back!"
            +Gestures.Smile
            +delay(1000)
            +"I will watch your progress and may offer help and useful hints for solving the problems."
            +"Go ahead and start answering now. Best of luck"
        }

        furhat.say(textq1)

        call(QuizQuestion)
        //println("Quiz state - after 1st question")

    }

    // Notification from GUI to Furhat
    onEvent(NOTIFICATION) {
        val data = it.get("data").toString()
        println("Quiz state - Next Question -  Question No : $data")

        // Hardcoded value of 6 to exit from this loop
        if (data?.toInt() > 5) {
            //println("Terminate from Quiz state")
            terminate()
        } else {
            // Next question notification
            //println("Quiz state - Go to next question")
            call(QuizQuestion)
            //println("Quiz state - after question 2 onwards")

        }
    }

}


val QuizQuestion = state(Interaction) {

    var counter = 0

    onEntry {
        //println("QuizQuestion state")
    }

    onTime(delay=20000) {
        counter = 1
        users.current.quiz.promptHelp1 = true
        furhat.ask("Do you need any help?", timeout = 10000)
    }

    onTime(delay=50000) {
        counter = 2
        users.current.quiz.promptHelp2 = true
        furhat.ask("Do you need any help?", timeout = 10000)
    }

    //onTime(delay=15000) {
    //    counter = 3
    //    furhat.ask("Do you need any help?", timeout = 3000)
    //}

    onResponse<No> {
        //println("QuizQuestion state - NO - Counter: $counter")
        furhat.say("Okay! No Problem")
    }

    onResponse<Yes> {
        //println("QuizQuestion state - YES  - Counter: $counter")
        send(HELPTEXT)
        furhat.say("I have sent you some hints to your help box. Hope that helps!")
        if (users.current.quiz.promptHelp1){
            users.current.quiz.help1 = true
        }
        if (users.current.quiz.promptHelp2){
            users.current.quiz.help2 = true
        }
    }

    onNoResponse { // Catches silence
        //println("QuizQuestion state - NO Response  - Counter: $counter")
        furhat.say("I didn't hear anything")
        users.current.quiz.noUserResponse = true
    }

    // Notification from GUI to Furhat
    onEvent(MESSAGE) {

        val data = it.get("data").toString()
        furhat.say(data ?: "Something went wrong")
        users.current.quiz.comments = mutableListOf(data)

        // Let the GUI know we're done speaking, to unlock buttons
        send(SPEECH_DONE)

        println("Message Received from GUI")
    }

    // Notification from GUI to Furhat
    onEvent(NOTIFICATION) {
        println("QuizQuestion state - Submit Answer -  Counter: $counter")
        val data = it.get("data").toString()
        users.current.quiz.questionId = data
        //println("QuizQuestion state - Submit Answer -  Question No : $data")

        // Print Quiz Performance data
        println("User: ${users.current.registrationId}")
        println("Question Id: ${users.current.quiz.questionId}")
        println("Help 1 offered: ${users.current.quiz.promptHelp1}")
        println("Help 1 used: ${users.current.quiz.help1}")
        println("Help 2 offered: ${users.current.quiz.promptHelp2}")
        println("Help 2 used: ${users.current.quiz.help2}")
        println("No User Response for help: ${users.current.quiz.noUserResponse}")
        println("Answer?: ${users.current.quiz.comments}")
        // Terminate when user submits the answer
        terminate()
    }

}

val Lecture = state(Interaction) {
    onEntry {
        println("Lecture state")

        send(ScreenDelivery(screen = introscreen))
        furhat.say(intro)

        //send(ScreenDelivery(screen = carimg))
        //furhat.say(session_car)

        // send agenda
        //send(ScreenDelivery(screen = agendaimg))
        //furhat.say(agenda)

        call(LectureQuestionOne)
        //call(BoxOnTableExampleConfirmSecondTime)

        send(ScreenDelivery(screen = screen12))
        furhat.say(session_screen12)

        call(LectureQuiz)

        terminate()

    }

}

val LectureQuestionOne = state(Interaction) {

    onEntry {
        println("Lecture - QuestionOne")

        send(ScreenDelivery(screen = screen1))
        furhat.ask(session_screen1, timeout = 10000)

    }

    onResponse<No> {
        furhat.say(session_screen1_no)
        terminate()
    }

    onResponse<Yes> {
        furhat.say(session_screen1_yes)
        call(TugOfWarExample)
        terminate()
    }

    onNoResponse { // Catches silence
        furhat.ask("I didn't hear anything", timeout = 10000)
    }

}

val TugOfWarExample = state(Interaction) {

    onEntry {
        println("Lecture - TugOfWarExample")

        send(ScreenDelivery(screen = screen2))
        furhat.say(session_screen2)

        send(ScreenDelivery(screen = screen3))
        furhat.say(session_screen3)

        send(ScreenDelivery(screen = screen4))
        furhat.say(session_screen4)

        send(ScreenDelivery(screen = screen4a))
        furhat.say(session_screen4a)

        //furhat.ask(session_screen4a_review, timeout = 10000)
        terminate()
    }

    onResponse<No> {
        furhat.say("Great! Let's move on to the next slide")
        terminate()
    }

    onResponse<Yes> {
        furhat.say(session_screen4a_yes)
        call(BoxOnTableExample)
        terminate()
    }

    onNoResponse { // Catches silence
        furhat.ask("I didn't hear anything", timeout = 10000)
    }

}

val BoxOnTableExample = state(Interaction) {

    onEntry {
        println("Lecture - BoxOnTableExample")

        send(ScreenDelivery(screen = screen5))
        furhat.say(session_screen5)

        call(BoxOnTableExampleConfirm)

        send(ScreenDelivery(screen = screen7))
        furhat.say(session_screen7)

        send(ScreenDelivery(screen = screen8))
        furhat.say(session_screen8)

        send(ScreenDelivery(screen = screen9))
        furhat.say(session_screen9)

        send(ScreenDelivery(screen = screen10))
        furhat.say(session_screen10)

        terminate()

    }

}

val BoxOnTableExampleConfirm = state(Interaction) {

    onEntry {
        println("Lecture - BoxOnTableExampleConfirm")

        send(ScreenDelivery(screen = screen6))
        furhat.say(session_screen6)

        furhat.ask("Does this sound ok?", timeout = 10000)

    }

    onResponse<No> {
        furhat.say("Don't worry. I will explain in detail")
        terminate()
    }

    onResponse<Yes> {
        furhat.say(session_screen6_review_yes)
        terminate()
    }

    onNoResponse { // Catches silence
        furhat.ask("I didn't hear anything", timeout = 10000)
    }

}

val BoxOnTableExampleConfirmSecondTime = state(Interaction) {

    onEntry {
        println("Lecture - BoxOnTableExampleConfirmSecondTime")

        send(ScreenDelivery(screen = screen11))
        furhat.ask(session_screen11, timeout = 10000)

    }

    onResponse<No> {
        furhat.say("Don't worry. I will explain in detail")
        terminate()
    }

    onResponse<Yes> {
        furhat.say(session_screen11_question_yes)
        terminate()
    }

    onNoResponse { // Catches silence
        furhat.ask("I didn't hear anything", timeout = 10000)
    }

}

val LectureQuiz = state(Interaction) {

    onEntry {
        println("Lecture - LectureQuiz")

        send(ScreenDelivery(screen = quiz_screen1))
        furhat.ask(session_quiz_screen1, timeout = 30000)

    }

    //onTime(delay=40000) {
    //    furhat.ask("Do you need any help?", timeout = 10000)
    //}


    onResponse<No> {
        furhat.ask("Okay. Waiting for you to answer!", timeout = 10000)
    }

    onResponse<Yes> {
        send(ScreenDelivery(screen = quiz_screen2))
        furhat.say(session_quiz_screen2)
        furhat.ask("Hope that helps. Try to answer now!", timeout = 10000)
    }

    onNoResponse { // Catches silence
        furhat.ask(session_quiz_screen1_help, timeout = 10000)
    }

    onResponse<True> {
        furhat.say("I am afraid. That's the wrong answer.")
        terminate()
    }

    onResponse<False> {
        send(ScreenDelivery(screen = quiz_screen3))
        furhat.say(session_quiz_screen3)
        terminate()
    }

}


val LectureFirstLaw = state(Interaction) {

    onEntry {
        println("Lecture - Newton's Second Law of Motion")

        val session1_2 = utterance {
            +"Lets elaborate it a little more."
            +delay(1000)
        }

        furhat.say(session1_2)

        // send first law screen 1
        send(ScreenDelivery(screen = fl1a))
        furhat.say(session_fl1a)

        send(ScreenDelivery(screen = fl1b))
        furhat.say(session_fl1b)

        send(ScreenDelivery(screen = fl1c))
        furhat.say(session_fl1c)

        send(ScreenDelivery(screen = fl1d))
        furhat.say(session_fl1d)

        send(ScreenDelivery(screen = fl1e))
        furhat.say(session_fl1e)

        send(ScreenDelivery(screen = fl1f))
        furhat.say(session_fl1f)

        // First Law Stationary Object - Zero Force
        send(ScreenDelivery(screen = fl4))
        furhat.say(session_fl4)

        send(ScreenDelivery(screen = fl5))
        furhat.say(session_fl5)

        // First Law Moving Object
        send(ScreenDelivery(screen = fl6))
        furhat.say(session_fl6)

        // First Law Moving Object
        send(ScreenDelivery(screen = fl7))
        furhat.say(session_fl7)

        // First Law Moving Object
        send(ScreenDelivery(screen = fl8))
        furhat.say(session_fl8)

        // First Law - Moving Car
        send(ScreenDelivery(screen = fl9a))
        furhat.say(session_fl9a)

        // First Law - Moving Car
        send(ScreenDelivery(screen = fl9))
        furhat.say(session_fl9)

        send(ScreenDelivery(screen = fl10))
        furhat.say(session_fl10)

        // First Law - Non-Zero
        send(ScreenDelivery(screen = fl11))
        furhat.say(session_fl11)

        // First Law - Non-Zero
        send(ScreenDelivery(screen = fl12a))
        furhat.say(session_fl12a)

        // First Law - Non-Zero
        send(ScreenDelivery(screen = fl12))
        furhat.say(session_fl12)

        // First Law - Non-Zero
        send(ScreenDelivery(screen = fl13))
        furhat.say(session_fl13)

        // First Law - Non-Zero
        send(ScreenDelivery(screen = fl14))
        furhat.say(session_fl14)

        // First Law - Non-Zero
        send(ScreenDelivery(screen = fl15))
        furhat.say(session_fl15)

        // First Law - Non-Zero
        send(ScreenDelivery(screen = fl16))
        furhat.say(session_fl16)

        // First Law - Non-Zero
        send(ScreenDelivery(screen = fl17))
        furhat.say(session_fl17)

        // First Law - Non-Zero
        send(ScreenDelivery(screen = fl18))
        furhat.say(session_fl18)

        // First Law - Non-Zero
        send(ScreenDelivery(screen = fl19))
        furhat.say(session_fl19)

        // First Law - Non-Zero
        send(ScreenDelivery(screen = fl20))
        furhat.say(session_fl20)

        // First Law - Non-Zero
        send(ScreenDelivery(screen = fl21))
        furhat.say(session_fl21)

        // First Law - Non-Zero
        send(ScreenDelivery(screen = fl22))
        furhat.say(session_fl22)

        // First Law - Non-Zero
        send(ScreenDelivery(screen = fl23))
        furhat.say(session_fl23)

        // First Law - Non-Zero
        send(ScreenDelivery(screen = fl24))
        furhat.say(session_fl24)

        // First Law - Non-Zero
        send(ScreenDelivery(screen = fl25))
        furhat.say(session_fl25)

        // First Law - Non-Zero
        send(ScreenDelivery(screen = fl26))
        furhat.say(session_fl26)

        // send screen
        send(ScreenDelivery(screen = fl_text1))
        furhat.say(session_fl_text1)

        // send screen
        send(ScreenDelivery(screen = fl_text2))
        furhat.say(session_fl_text2)

        // send session_fl_planet - Earth moving around sun
        send(ScreenDelivery(screen = fl_planet))
        furhat.say(session_fl_planet)

        call(FirstLawQuestionOne)

        call(FirstLawQuestionTwo)

        terminate()

    }

}

val FirstLawQuestionOne = state(Interaction) {

    onEntry {
        println("Lecture - FirstLawQuestionOne")

        //first law question screen
        send(ScreenDelivery(screen = fl_question1))
        furhat.ask(session_fl_question1, timeout = 20000)

    }

    //onTime(delay=40000) {
    //    furhat.ask("Do you need any help?", timeout = 10000)
    //}


    onResponse<No> {
        furhat.ask("Okay. Waiting for you to answer!", timeout = 30000)
    }

    onResponse<Yes> {
        furhat.say(session_fl_question1_tip)
        furhat.ask("Hope that helps. Try to answer now!", timeout = 30000)
    }

    onNoResponse { // Catches silence
        furhat.say("I didn't hear anything")
        furhat.ask("Do you need any help?", timeout = 5000)
    }

    onResponse<True> {
        furhat.say(session_fl_question1_correct)
        terminate()
    }

    onResponse<False> {
        furhat.say(session_fl_question1_wrong)
        terminate()
    }

}

val FirstLawQuestionTwo = state(Interaction) {

    onEntry {
        println("Lecture - FirstLawQuestionTwo")

        send(ScreenDelivery(screen = fl_question2))
        furhat.ask(session_fl_question2, timeout = 15000)

    }

    onResponse<No> {
        furhat.ask("Okay. Waiting for you to answer!", timeout = 30000)
    }

    onResponse<Yes> {
        furhat.say(session_fl_question2_tip)
        furhat.ask("Hope that helps. Try to answer now!", timeout = 30000)
    }

    onNoResponse { // Catches silence
        furhat.say("I didn't hear anything")
        furhat.ask("Do you need any help?", timeout = 10000)
    }

    onResponse<True> {
        furhat.say(session_fl_question2_correct)

        send(ScreenDelivery(screen = fl_question2_ans))
        furhat.say(session_fl_question2_ans)
        terminate()
    }

    onResponse<False> {
        furhat.say(session_fl_question2_wrong)

        send(ScreenDelivery(screen = fl_question2_ans))
        furhat.say(session_fl_question2_ans)
        terminate()
    }

}

val LectureSecondLaw = state(Interaction) {

    onEntry {
        println("Lecture - Newton's Second Law of Motion")

        //
        send(ScreenDelivery(screen = sl1))
        furhat.say(session2_sl1)

        //
        send(ScreenDelivery(screen = sl2))
        furhat.say(session2_sl2)

        //
        send(ScreenDelivery(screen = sl3))
        furhat.say(session2_sl3)

        //
        send(ScreenDelivery(screen = sl4))
        furhat.say(session2_sl4)

        //
        send(ScreenDelivery(screen = sl5))
        furhat.say(session2_sl5)

        //
        send(ScreenDelivery(screen = sl6))
        furhat.say(session2_sl6)

        //
        send(ScreenDelivery(screen = sl7))
        furhat.say(session2_sl7)

        //
        send(ScreenDelivery(screen = sl8))
        furhat.say(session2_sl8)

        send(ScreenDelivery(screen = fl27))
        furhat.say(session_fl27)

        send(ScreenDelivery(screen = fl28))
        furhat.say(session_fl28)

        //
        send(ScreenDelivery(screen = thanks))
        furhat.say(session2_thanks)

        terminate()

    }

}
