package furhatos.app.furgui

import furhatos.event.Event

/*
    Variables and events
 */
val PORT = 1234 // GUI Port
val SPEECH_DONE = "SpeechDone"
val HELPTEXT = "HelpText"

// Event used to pass data to GUI
class DataDelivery(
        val buttons: List<String>,
        val inputFields: List<String>?
) : Event()




