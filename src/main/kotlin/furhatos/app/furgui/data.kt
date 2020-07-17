package furhatos.app.furgui

// Buttons
val buttons = listOf("Register", "Pre-Test", "Lecture", "Quiz")

/*
 Input fields, each with a answer to be spoken. The answer is defined as a lambda
 function since we want to have different answers depending on what favorite robot the
 user inputs
  */
val inputFieldData = mutableMapOf<String, (String) -> String>(
    "Registration Number" to { ID -> "Your unique registration Number is $ID" }
)


