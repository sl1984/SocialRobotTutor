package furhatos.app.furgui

import furhatos.event.senses.SenseSkillGUIConnected
import furhatos.flow.kotlin.*
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
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
            //text = ("This is the initial test, which will check your knowledge of the subject. Best of Luck")
            call(PreTest)
        } else if (data == "Lecture") {
            text = ("Lets start with the Lecture session")
        } else if (data == "Quiz") {
            text = ("This is the final quiz. If you need any help to solve the problems, then please ask me." +
                    " I can provide you useful hints and help")
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

        val instruction = "Please take the test by clicking the button Pre-Test"
        furhat.say(instruction)

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

        if (eventType == "PRETESTSTARTED") {
            call(PreTest)
        }

        furhat.say(message ?: "Something went wrong")

        // Let the GUI know we're done speaking, to unlock buttons
        send(SPEECH_DONE)

        println("Message Received from GUI")
    }


}

val PreTest = state {

    onEntry {
        println("PreTest state - start")
        val text = ("Welcome! This is the initial test, which will check your knowledge of the subject. Best of Luck")
        furhat.say(text)

        call(PreTestQuestion)
        println("PreTest state - after 1st question")

    }

    // Notification from GUI to Furhat
    onEvent(NOTIFICATION) {
        val data = it.get("data").toString()
        println("PreTest state - Next Question -  Question No : $data")

        // Hardcoded value of 6 to exit from this loop
        if (data?.toInt() > 5) {
            println("Terminate from PreTest state")
            terminate()
        } else {
            // Next question notification
            println("PreTest state - Go to next question")
            call(PreTestQuestion)
            println("PreTest state - after question 2 onwards")

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
        println("PreTestQuestion state - NO - Counter: $counter")
        furhat.say("Okay! Help No ")
    }

    onResponse<Yes> {
        println("PreTestQuestion state - YES  - Counter: $counter")
        send(HELPTEXT)
        furhat.say("Check your help box to see the hints")
    }

    onNoResponse { // Catches silence
        println("PreTestQuestion state - NO Response  - Counter: $counter")
        furhat.say("I didn't hear anything")
    }

    // Notification from GUI to Furhat
    onEvent(NOTIFICATION) {
        println("PreTestQuestion state - Submit Answer -  Counter: $counter")
        val data = it.get("data").toString()
        println("PreTestQuestion state - Submit Answer -  Question No : $data")
        // Terminate when user submits the answer
        terminate()
    }

}
