package furhatos.app.furgui.lecture

import furhatos.flow.kotlin.utterance
import furhatos.gestures.Gestures
import furhatos.records.Location

val introscreen = listOf("intro.gif")
val intro = utterance {
    +"Hello. Welcome to the session on Force and Motion."
    +"I am Furhat, your tutor."
    +Gestures.Smile(1.0, 2.0)
    +delay(1000)
    +"In this five minute tutorial, we will learn about how force works."
    +Gestures.Smile
    +delay(1000)
}

val carimg = listOf("movingcar.gif")
val session_car = utterance {
    +"Have you ever wondered about the science behind how things move? "
    +attend(Location.UP_RIGHT)
    +glance(Location.UP_RIGHT)
    +delay(1000)
    +"We often use Newtons laws of motion to describe force and its resultant acceleration"
    +" on just about any everyday object, such as a moving car, free falling object or the "
    +"operation of an elevator."
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +delay(2000)
}

val agendaimg = listOf("agenda.png")

val agenda = utterance {
    +Gestures.Smile
    +"In this 15 minute tutorial session, we will learn about how newton's laws work."
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +delay(2000)
    +"After this session, you should be able to understand and apply Newton’s first and second laws of motion."
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +delay(3000)
}

// First Law - Question 1
val fl_question1 = listOf("fl_question1.gif")
val session_fl_question1 = utterance {
    +"Here is your first question related to Newton's First Law of Motion."
    +delay(1000)
    +"You need to tell whether the following statement is true of false."
    +delay(2000)
    +"If the net force on a body is zero, then its velocity will not change"
}

val session_fl_question1_tip = utterance {
    +"According to Newton’s First Law, an object remains in the same state of motion unless"
    +"a resultant force acts on it."
    +Gestures.Smile
    +delay(2000)
}

val session_fl_question1_correct = utterance {
    +Gestures.BigSmile
    +"Well done that's absolutely correct. In fact it's another way to describe Newton's First Law of Motion"
    +delay(3000)
}

val session_fl_question1_wrong = utterance {
    +Gestures.ExpressSad
    +"I'm afraid that's not the correct answer. A net resultant force is required in order to change the "
    +"velocity of an object"
    +delay(2000)
    +"According to Newton’s First Law, an object remains in the same state of motion unless"
    +"a resultant force acts on it."
    +delay(3000)
}


// First Law Second Question
val fl_question2 = listOf("fl_question2.gif")

val session_fl_question2 = utterance {
    +"Let’s move on to another question."
    +delay(1000)
    +"Is the following statement true or false. "
    +delay(2000)
    +"Objects in orbit around the Earth, like a satellite, must have a net force acting on them."
}

val session_fl_question2_tip = utterance {
    +"An object in orbit may have a constant speed, but its direction is constantly changing as it"
    +"moves in a circle and, thus, its velocity is also changing."
    +delay(2000)
}

val session_fl_question2_correct = utterance {
    +Gestures.BigSmile
    +"You’re doing really well! That is right"
    +delay(3000)
}

val fl_question2_ans = listOf("fl_question2_ans.gif")

val session_fl_question2_ans = utterance {
    +"A good example of this is circular motion. "
    +glance(Location.RIGHT, 5000)
    +delay(1000)
    +"An object with no net forces acting on it would not have a change in velocity. "
    +"If it is stationary, it would stay stationary. If it is in motion, it will stay in motion with "
    +"a fixed velocity, moving in a straight line. This comes directly out of Newton's First Law of Motion."
    +delay(1000)
    +"An object in orbit may have a constant speed, but its direction is constantly changing as it "
    +"moves in a circle and, thus, its velocity is also changing. Remember, velocity takes into"
    +" consideration speed and direction. Therefore, there must be a net force acting on it."
    +" This is the net force of Earth's gravity acting on the object."
    +delay(3000)
}

val session_fl_question2_wrong = utterance {
    +Gestures.ExpressSad
    +"I suppose, that wasn’t the correct answer."
    +delay(2000)
}



