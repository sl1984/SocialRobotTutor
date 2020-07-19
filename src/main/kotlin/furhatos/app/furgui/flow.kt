package furhatos.app.furgui

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
            val text = ("Welcome! This is the initial test, which will check your knowledge of the subject. Best of Luck")
            furhat.say(text)
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
        }

        furhat.say(textq1)

        call(QuizQuestion)
        //println("Quiz state - after 1st question")

    }

    // Notification from GUI to Furhat
    onEvent(NOTIFICATION) {
        val data = it.get("data").toString()
        //println("Quiz state - Next Question -  Question No : $data")

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

        //println("Message Received from GUI")
    }

    // Notification from GUI to Furhat
    onEvent(NOTIFICATION) {
        //println("QuizQuestion state - Submit Answer -  Counter: $counter")
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
        val introscreen = listOf("intro.gif")
        send(ScreenDelivery(screen = introscreen))

        val intro = utterance {
            +"Hello. Welcome to the session on Force and Motion."
            +Gestures.Smile
            +delay(2000)
        }

        furhat.say(intro)

        val carimg = listOf("movingcar.gif")
        send(ScreenDelivery(screen = carimg))

        val session1_1 = utterance {
            +"Have you ever wondered about the science behind how things move? "
            +delay(1000)
            +"We often use Newtons laws of motion to describe force and its resultant acceleration"
            +" on just about any everyday object, such as a moving car, free falling object or the "
            +"operation of an elevator."
            +delay(2000)
        }

        furhat.say(session1_1)

        // send screen 2
        val agendaimg = listOf("agenda.png")
        send(ScreenDelivery(screen = agendaimg))

        val agenda = utterance {
            +Gestures.Smile
            +"In this 10 minute tutorial session, we will learn about how newton's laws work."
            +delay(1000)
            +"After this session, you should be able to understand and apply Newton’s first and second laws of motion."
            +delay(3000)
        }

        furhat.say(agenda)

        call(LectureFirstLaw)

        furhat.ask("Shall we move onto the Second Law of Motion?", timeout = 3000)

        //terminate()

    }

    onResponse<No> {
        println("Shall we move onto the Second Law of Motion? - NO")
        furhat.say("Okay! The Lecture session ends here. ")
        terminate()
    }

    onResponse<Yes> {
        println("Shall we move onto the Second Law of Motion? - YES")
        furhat.say("Okay! Moving onto Newton's Second Law of Motion")
        call(LectureSecondLaw)
        terminate()
    }

    onNoResponse { // Catches silence
        println("Shall we move onto the Second Law of Motion? - NO Response")
        furhat.say("Okay! Moving onto Newton's Second Law of Motion")
        call(LectureSecondLaw)
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
        val fl1 = listOf("fl1.gif")
        send(ScreenDelivery(screen = fl1))

        val session_fl1 = utterance {
            +"So, lets look at what Newtons Law states."
            +delay(1000)
            +glance(Location.RIGHT, 5000)
            +"According to Newton’s First Law, "
            +"an object remains in the same state of motion, unless a resultant force acts on it."
            +delay(2000)
        }

        furhat.say(session_fl1)

        // send first law screen 2
        val fl2 = listOf("fl2.gif")
        send(ScreenDelivery(screen = fl2))

        val session_fl2 = utterance {
            +"Or in other words, If the resultant force acting on a stationary object is zero, then the object "
            +"will remain stationary."
            +delay(1000)
        }

        furhat.say(session_fl2)

        // send first law screen 3
        val fl3 = listOf("fl3.gif")
        send(ScreenDelivery(screen = fl3))

        val session_fl3 = utterance {
            +"It is also applicable to moving objects.  So, if the resultant force acting on a moving object is "
            +"zero, then the object will continue moving in the same direction with the same speed, or in "
            +"other words, with the same velocity"
            +delay(1000)
        }

        furhat.say(session_fl3)

        // First Law Stationary Object - Zero Force
        val fl4 = listOf("fl4.gif")
        send(ScreenDelivery(screen = fl4))

        val session_fl4 = utterance {
            +"Let’s elaborate it a little more."
            +glance(Location.RIGHT, 8000)
            +"Consider this object. There are no forces acting on it currently and therefore it is stationary."
            +delay(2000)
        }

        furhat.say(session_fl4)

        // First Law Stationary Object - Zero Force
        val fl5 = listOf("fl5.gif")
        send(ScreenDelivery(screen = fl5))

        val session_fl5 = utterance {
            +"Now, imagine is there a 50 Newton force acting on its right and a 50 Newton Force acting on its left."
            +" In this case the forces are balanced. We can  find out the resultant force by taking the difference"
            +" between the two forces, that is, fifty minus fifty. Therefore  the resultant force acting on this"
            +" object is zero. So, this object continues to remains stationary."
            +delay(2000)
        }

        furhat.say(session_fl5)

        // First Law Moving Object
        val fl6 = listOf("fl6.gif")
        send(ScreenDelivery(screen = fl6))

        val session_fl6 = utterance {
            +"What if the object was moving at a constant velocity, say two meters per second?"
            +delay(2000)
        }

        furhat.say(session_fl6)

        // First Law Moving Object
        val fl7 = listOf("fl7.gif")
        send(ScreenDelivery(screen = fl7))

        val session_fl7 = utterance {
            +"Now we apply a 50 newton force on its right and another 50 newton force on its left.  "
            +"Once again the forces are balanced and the resultant force is zero. So, the object continues"
            +" to maintain its state."
            +delay(2000)
        }

        furhat.say(session_fl7)

        // First Law Moving Object
        val fl8 = listOf("fl8.gif")
        send(ScreenDelivery(screen = fl8))

        val session_fl8 = utterance {
            +"It will carry on moving with the same speed of two meters per second in the same direction"
            +delay(2000)
        }

        furhat.say(session_fl8)

        // First Law - Moving Car
        val fl9 = listOf("fl9.gif")
        send(ScreenDelivery(screen = fl9))

        val session_fl9 = utterance {
            +"Let’s take a look at a car moving at a constant velocity of ten meters per second. You can see that"
            +" the driving force of the engine is acting towards the right. Because the car is moving at a "
            +"constant velocity, there should be an equal force acting in the opposite direction. "
            +"This is a natural force that occurs called the resistive force. It includes both friction "
            +"with the air and friction with the road."
            +delay(2000)
        }

        furhat.say(session_fl9)

        // First Law - Non-Zero
        //val fl10 = listOf("fl10.gif")
        //send(ScreenDelivery(screen = fl10))

        //val session_fl10 = utterance {
        //   +""
        //   +delay(2000)
        //}

        //furhat.say(session_fl10)

        // First Law - Non-Zero
        val fl11 = listOf("fl11.gif")
        send(ScreenDelivery(screen = fl11))

        val session_fl11 = utterance {
            +"From Newton’s first law, we can understand that the velocity of an object will only change "
            +"if a resultant force is acting on it."
            +delay(3000)
        }

        furhat.say(session_fl11)

        // First Law - Non-Zero
        val fl12a = listOf("fl12a.gif")
        send(ScreenDelivery(screen = fl12a))

        val session_fl12a = utterance {
            +"Let’s look at an example. This is a stationary object."
            +delay(2000)
        }

        furhat.say(session_fl12a)

        // First Law - Non-Zero
        val fl12 = listOf("fl12.gif")
        send(ScreenDelivery(screen = fl12))

        val session_fl12 = utterance {
            +"If you apply  fifteen newton force to this object, towards the right direction "
            +delay(2000)
        }

        furhat.say(session_fl12)

        // First Law - Non-Zero
        val fl13 = listOf("fl13.gif")
        send(ScreenDelivery(screen = fl13))

        val session_fl13 = utterance {
            +"and five newton to the left direction, then it will result in an unbalanced force that leads to "
            +"a net resultant force acting on the object"
            +delay(2000)
        }

        furhat.say(session_fl13)

        // First Law - Non-Zero
        val fl14 = listOf("fl14.gif")
        send(ScreenDelivery(screen = fl14))

        val session_fl14 = utterance {
            +"This resultant force causes the object to accelerate to the right. That was pretty straight forward, "
            +"since the object was initially in stationary position. Now, depending on the initial state of the object, the resultant "
            +"force can cause different things happening to the object"
            +delay(2000)
        }

        furhat.say(session_fl14)

        // First Law - Non-Zero
        val fl15 = listOf("fl15.gif")
        send(ScreenDelivery(screen = fl15))

        val session_fl15 = utterance {
            +"For example, if the object was already moving in the right direction at two meters per second "
            +delay(1000)
        }

        furhat.say(session_fl15)

        // First Law - Non-Zero
        val fl16 = listOf("fl16.gif")
        send(ScreenDelivery(screen = fl16))

        val session_fl16 = utterance {
            +"Then a resultant force applied on it towards the right direction"
            +delay(1000)
        }

        furhat.say(session_fl16)

        // First Law - Non-Zero
        val fl17 = listOf("fl17.gif")
        send(ScreenDelivery(screen = fl17))

        val session_fl17 = utterance {
            +"will cause it to, speed up, or, in other words, accelerate, towards the right"
            +delay(3000)
        }

        furhat.say(session_fl17)

        // First Law - Non-Zero
        val fl18 = listOf("fl18.gif")
        send(ScreenDelivery(screen = fl18))

        val session_fl18 = utterance {
            +"What if this object was moving towards the left at two meters per second?"
            +delay(1000)
        }

        furhat.say(session_fl18)

        // First Law - Non-Zero
        val fl19 = listOf("fl19.gif")
        send(ScreenDelivery(screen = fl19))

        val session_fl19 = utterance {
            +"Now, if you add a resultant force in the opposite direction towards the right."
            +delay(1000)
        }

        furhat.say(session_fl19)

        // First Law - Non-Zero
        val fl20 = listOf("fl20.gif")
        send(ScreenDelivery(screen = fl20))

        val session_fl20 = utterance {
            +"Then, the object will slow down to, say, one meter per second."
            +delay(3000)
        }

        furhat.say(session_fl20)

        // First Law - Non-Zero
        val fl21 = listOf("fl21.gif")
        send(ScreenDelivery(screen = fl21))

        val session_fl21 = utterance {
            +"What if the object was moving towards the left slowly?"
            +delay(1000)
        }

        furhat.say(session_fl21)

        // First Law - Non-Zero
        val fl22 = listOf("fl22.gif")
        send(ScreenDelivery(screen = fl22))

        val session_fl22 = utterance {
            +"Then, a resultant force acting on the opposite direction"
            +delay(1000)
        }

        furhat.say(session_fl22)

        // First Law - Non-Zero
        val fl23 = listOf("fl23.gif")
        send(ScreenDelivery(screen = fl23))

        val session_fl23 = utterance {
            +"may cause it to stop."
            +delay(3000)
        }

        furhat.say(session_fl23)

        // First Law - Non-Zero
        val fl24 = listOf("fl24.gif")
        send(ScreenDelivery(screen = fl24))

        val session_fl24 = utterance {
            +"In all the cases we discussed so far, if you notice, the acceleration caused by the unbalanced force,"
            +" resulted in the object, to either start moving, speed up, slow down or stop."
            +delay(2000)
        }

        furhat.say(session_fl24)

        // First Law - Non-Zero
        val fl25 = listOf("fl25.gif")
        send(ScreenDelivery(screen = fl25))

        val session_fl25 = utterance {
            +"But what if the acceleration caused by the unbalanced force"
            +"only made the object change its direction and continue in the same speed."
            +delay(1000)
        }

        furhat.say(session_fl25)

        // First Law - Non-Zero
        val fl26 = listOf("fl26.gif")
        send(ScreenDelivery(screen = fl26))

        val session_fl26 = utterance {
            +"The object was initially moving to the right direction at two meters per second. A resultant force is"
            +" acting on it, in the upward direction. The object changes its direction, but continues with the same"
            +" speed of two meters per second"
            +delay(3000)
        }

        furhat.say(session_fl26)


        // send screen
        val fl_text1 = listOf("fl_text1.gif")
        send(ScreenDelivery(screen = fl_text1))

        val session_fl_text1 = utterance {
            +"Technically, acceleration is defined, as the change in velocity, over time."
            +delay(2000)
        }

        furhat.say(session_fl_text1)

        // send screen
        val fl_text2 = listOf("fl_text2.gif")
        send(ScreenDelivery(screen = fl_text2))

        val session_fl_text2 = utterance {
            +"Velocity is determined by, both speed and direction. So, any change in direction of the object, "
            +"also changes the velocity, and hence, causes acceleration."
            +delay(3000)
        }

        furhat.say(session_fl_text2)

        // send session_fl_planet - Earth moving around sun
        val fl_planet = listOf("fl_planet.gif")
        send(ScreenDelivery(screen = fl_planet))

        val session_fl_planet = utterance {
            +"A good example of this is circular motion. Consider the earth, revolving around the sun "
            +"in a circular motion. Even though, the speed may remain constant, but the direction keeps"
            +" changing. Therefore, the velocity keeps changing and causes it to accelerate."

            +delay(3000)
        }

        furhat.say(session_fl_planet)

        call(FirstLawQuestionOne)

        terminate()

    }

}

val FirstLawQuestionOne = state(Interaction) {

    onEntry {
        println("Lecture - FirstLawQuestionOne")

        //
        val fl_question1 = listOf("fl_question1.gif")
        send(ScreenDelivery(screen = fl_question1))

        val session2_fl_question1 = utterance {
            +"Here is your first question related to Newton's First Law of Motion."
            +delay(1000)
            +"You need to tell whether the following statement is true of false."
            +delay(2000)
            +"If the net force on a body is zero, then its velocity will not change"
        }

        furhat.ask(session2_fl_question1, timeout = 30000)


    }

    //onTime(delay=60000) {
    //    furhat.ask("Do you need any help?", timeout = 5000)
    //}

    //onTime(delay=60000) {
    //    terminate()
    //}


    onResponse<No> {
        println("FirstLawQuestionOne state - NO")
        furhat.ask("Okay. Waiting for you to answer!", timeout = 30000)
    }

    onResponse<Yes> {
        println("FirstLawQuestionOne state - YES")
        val session2_fl_question1_tip = utterance {
            +"According to Newton’s First Law, an object remains in the same state of motion unless"
            +"a resultant force acts on it."
            +delay(2000)
        }
        furhat.say(session2_fl_question1_tip)
        furhat.ask("Hope that helps. Try to answer now!", timeout = 30000)
    }

    onNoResponse { // Catches silence
        println("FirstLawQuestionOne state - NO Response")
        furhat.say("I didn't hear anything")
        furhat.ask("Do you need any help?", timeout = 5000)
    }

    onResponse<True> {
        println("FirstLawQuestionOne state - True")
        val session2_fl_question1_correct = utterance {
            +"Well done that's absolutely correct. In fact it's another way to describe Newton's First Law of Motion"
            +delay(3000)
        }

        furhat.say(session2_fl_question1_correct)
        terminate()
    }

    onResponse<False> {
        println("FirstLawQuestionOne state - False")
        val session2_fl_question1_tip = utterance {
            +"I'm afraid that's not the correct answer. A net resultant force is required in order to change the "
            +"velocity of an object"
            +delay(2000)
            +"According to Newton’s First Law, an object remains in the same state of motion unless"
            +"a resultant force acts on it."
            +delay(3000)
        }
        furhat.say(session2_fl_question1_tip)
        terminate()
    }

}

val LectureSecondLaw = state(Interaction) {

    onEntry {
        println("Lecture - Newton's Second Law of Motion")

        //
        val sl1 = listOf("sl1.gif")
        send(ScreenDelivery(screen = sl1))

        val session2_sl1 = utterance {
            +"We now know that an unbalanced  force will lead to a non resultant force acting on an object, "
            +"which will in turn cause the object to accelerate."
            +delay(2000)
        }

        furhat.say(session2_sl1)

        //
        val sl2 = listOf("sl2.gif")
        send(ScreenDelivery(screen = sl2))

        val session2_sl2 = utterance {
            +"So, a natural question arises? How much does the object accelerate. Well, this is dependant on two "
            +"factors , the amount of resultant force and the mass of the object"
            +delay(2000)
        }

        furhat.say(session2_sl2)

        //
        val sl3 = listOf("sl3.gif")
        send(ScreenDelivery(screen = sl3))

        val session2_sl3 = utterance {
            +"We learnt in newton’s first law that a resultant force is required to bring about an acceleration on "
            +"an object.  The net force needed is proportional to the mass of the object, that you are trying to"
            +" accelerate. These observations can be expressed using Newton’s second Law. Newton’s second Law"
            +" states that the resultant force is equal to mass times acceleration.  Now let’s try and understand"
            +" what we learnt with an example."
            +delay(2000)
        }

        furhat.say(session2_sl3)

        //
        val sl4 = listOf("sl4.gif")
        send(ScreenDelivery(screen = sl4))

        val session2_sl4 = utterance {
            +"The picture shows two objects of equal masses. On the first object, we apply a force of 50 newton, "
            +"and on the second object a 25 newton force is  applied. Both objects are experiencing a resultant"
            +" force, can you guess which object will experience an increased acceleration."
            +delay(2000)
        }

        furhat.say(session2_sl4)

        //
        val sl5 = listOf("sl5.gif")
        send(ScreenDelivery(screen = sl5))

        val session2_sl5 = utterance {
            +"From Newton’s second law, we now know that the acceleration is proportional to the resultant force acting"
            +" on the object.  What does this mean? It means that , if we have a greater force, then we have a greater"
            +" acceleration. So the first object will experience twice the amount of acceleration of 2 meters per"
            +" second square, as compared to the second object which accelerates at 1 meter per second square."
            +delay(2000)
        }

        furhat.say(session2_sl5)

        //
        val sl6 = listOf("sl6.gif")
        send(ScreenDelivery(screen = sl6))

        val session2_sl6 = utterance {
            +"Now, lets try to understand the relationship between mass and acceleration. In this screen, you can"
            +" observe two objects. Both of them experience the same amount of force. The top object has a mass"
            +" of 25 kilograms, where as the bottom object has a mass of 10 kilogram. Which object do you think will"
            +" experience an increased acceleration?"
            +delay(2000)
        }

        furhat.say(session2_sl6)

        //
        val sl7 = listOf("sl7.gif")
        send(ScreenDelivery(screen = sl7))

        val session2_sl7 = utterance {
            +"From Newton’s second law, we now know that , the acceleration is inversely proportional to the mass of "
            +"the object. In other words, if the mass is larger, the acceleration will be smaller. So, in this "
            +"case, the bottom object will experience twice the acceleration experienced by the top object."
            +delay(2000)
        }

        furhat.say(session2_sl7)

        //
        val sl8 = listOf("sl8.gif")
        send(ScreenDelivery(screen = sl8))

        val session2_sl8 = utterance {
            +"As seen in Newtons second law, In order to calculate the force required to accelerate an object, we use"
            +" the equation, force equals mass times acceleration, where force is measured in newtons, mass in "
            +"kilograms and acceleration in meters per second square. For example, we would have to exert 50 newton"
            +" force in order for an object of 25 kilograms to experience an acceleration of 2 meters per second square"
            +delay(2000)
        }

        furhat.say(session2_sl8)

        //
        val thanks = listOf("thanks.gif")
        send(ScreenDelivery(screen = thanks))

        val session2_thanks = utterance {
            +"That's the end of this lecture series on Newton's Laws of Motion"
            +"Hope you have enjoyed this session."
            +delay(1000)
            +"When you are ready, click the Quiz button to launch the practice tests."
            +delay(1000)
            +"We can make it more interactive and fun experience."
            +delay(2000)
        }

        furhat.say(session2_thanks)


        terminate()

    }

}
