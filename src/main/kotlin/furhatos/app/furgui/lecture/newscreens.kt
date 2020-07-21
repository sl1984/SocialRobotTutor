package furhatos.app.furgui.lecture

import furhatos.flow.kotlin.utterance
import furhatos.gestures.Gesture
import furhatos.gestures.Gestures
import furhatos.records.Location

val screen1 = listOf("screen1.gif")
val session_screen1 = utterance {
    +Gestures.Smile
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +"Let’s get started."
    +delay(1000)
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +"Have you ever played the tug of war game?"
    +delay(1000)
}
val session_screen1_yes = utterance {
    +Gestures.Nod(0.4, 1.0)
    +delay(1000)
    +"Great! So, then, let me try and explain how force works, using tug of war as an example"
    +Gestures.Smile(1.0, 2.0)
    +delay(2000)
}

val session_screen1_no = utterance {
    +Gestures.Surprise
    +"Okay. Lets find out another example for you!"
    +delay(1000)
}

// Tug of War Example
val screen2 = listOf("screen2.gif")
val session_screen2 = utterance {
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +"I am sure you would  have experienced a push or a pull while playing tug of war."
    +delay(1000)
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +"Force is defined as a push or pull that acts on an object."
    +delay(1000)
}

val screen3 = listOf("screen3.gif")
val session_screen3 = utterance {
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +"When you play tug of war, you may have come across a situation, where, one team is pulling the other team towards them."
    +delay(500)
    +"This is because the magnitude of force exerted by the first team is higher, "
    +"and the net force is acting towards the direction of team exerting the larger force."
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +delay(1000)
}

val screen4 = listOf("screen4.gif")
val session_screen4 = utterance {
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +delay(500)
    +"Forces, come in pairs. In every interaction, there is a pair of forces acting on the two interacting objects."
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +"The magnitude of forces on the first object equals, the magnitude of forces on the second object."
    +delay(1000)
}

val screen4a = listOf("screen4a.gif")
val session_screen4a = utterance {
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +"Now, I want you to focus on Person B."
    +delay(500)
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +delay(500)
    +"I would like to show you all the forces acting on Person B."
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +"A person standing on ground, experiences the force of gravity."
    +"Can you see a force of gravity acting downwards in the picture? "
    +delay(500)
    +"This downward weight of gravity, is balanced by the upward push of the ground called the reactive force,"
    +" as shown in green."
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +delay(500)
    +"When person A pulls the rope, there is a tension exerted on Person B. "
    +delay(500)
    +"This force  tries to pull him towards Person A."
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +delay(500)
    +"Finally, there is a force of friction, caused by the person sliding against the ground."
    +"This acts towards the opposite direction of tension."
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +delay(1000)
}

// Tug Of War review
val session_screen4a_review = utterance {
    +delay(3000)
    +Gestures.Thoughtful(1.0, 2.0)
    +delay(2000)
    +"If there is something you don’t understand, that’s fine, we can go through another example."
    +delay(500)
    +"Would you like me to show you a simpler example?"
    +Gestures.Smile(1.0, 2.0)
}

val session_screen4a_yes = utterance {
    +Gestures.Smile(1.0, 1.0)
    +Gestures.Nod(0.4, 1.0)
    +delay(1000)
    +"Sure. I'm happy to explain the concept of paired nature of forces, using a simpler example"
}

// Box on Table Example
val screen5 = listOf("screen5.gif")
val session_screen5 = utterance {
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +"In this picture a box is at rest on a table."
    +delay(500)
    +"It experiences a pair of forces, equal and opposite."
    +delay(500)
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +"Let’s find out what they are."
    +delay(2000)
}

val screen6 = listOf("screen6.gif")
val session_screen6 = utterance {
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +"One is the force of gravity acting downwards."
    +delay(500)
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +"And the other is the opposite  reaction force upwards. "
    +delay(2000)
}

val session_screen6_review_yes = utterance {
    +Gestures.Smile
    +Gestures.Nod(0.6, 2.0)
    +delay(1000)
    +"Great!"
    +delay(500)
    +"Let's move on to the next slide"
    +delay(1000)
}

val screen7 = listOf("screen7.gif")
val session_screen7 = utterance {
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +"Take a look at this picture."
    +delay(1000)
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +"Now, if you apply some force and tilt the table, the box starts sliding down."
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +delay(500)
    +"Apart from the force of gravity and reaction force, a new force is experienced by the box."
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +delay(1000)
}

val screen8 = listOf("screen8.gif")
val session_screen8 = utterance {
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +"This is the force of friction."
    +delay(500)
    +"It is experienced when two objects slide past each other."
    +delay(500)
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +"Since the box is sliding downwards, friction will act exactly in its opposite direction."
    +delay(2000)
}

val screen9 = listOf("screen9.gif")
val session_screen9 = utterance {
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +"Now imagine that, this box is hung on the ceiling using a rope."
    +delay(500)
    +"As you can see, there is a force of gravity acting downwards."
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +"There is also another force that comes into play here. What could this be?"
    +delay(1000)
}

val screen10 = listOf("screen10.gif")
val session_screen10 = utterance {
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +"An object that is being stretched experiences a tension force."
    +delay(500)
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +"This will act exactly in the opposite direction of the gravitational force, which is pulling the box downwards"
    +delay(2000)
}

//Quiz
val quiz_screen1 = listOf("quiz_screen1.gif")
val session_quiz_screen1 = utterance {
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +"Now, can you go ahead and try to answer this question?"
    +delay(500)
    +"If an object is not moving, we can conclude that there are no forces acting on it."
    +delay(500)
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +"Is this statement true, or, false?"
}

val session_quiz_screen1_help = utterance {
    +Gestures.Thoughtful(1.0, 2.0)
    +delay(2000)
    +"Do you need any help?"
}

val quiz_screen2 = listOf("quiz_screen2.gif")
val session_quiz_screen2 = utterance {
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +"We learnt that forces can be both balanced and unbalanced."
    +delay(500)
    +"What happens when the forces on both sides of the tug of war are balanced?"
    +delay(500)
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
}

val quiz_screen3 = listOf("quiz_screen3.gif")
val session_quiz_screen3 = utterance {
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +delay(1500)
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +Gestures.Smile
    +Gestures.Nod(0.4, 2.0)
    +delay(2000)
    +"Well done!"
    +delay(500)
    +"In fact, we could have said true, if balanced forces was mentioned instead of, no forces."
    +delay(500)
    +"Congratulations, you have successfully completed the session on force and motion."
    +delay(2000)
}

val screen11 = listOf("screen11.gif")
val session_screen11 = utterance {
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +delay(1500)
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +"So, tell me. Is the concept of paired forces more clear now?"
}

val session_screen11_question_yes = utterance {
    +Gestures.Smile
    +Gestures.Nod(0.4, 2.0)
    +delay(2000)
    +"Brilliant!"
    +delay(500)
    +"Lets move on to the next slide."
    +delay(1000)
}


val screen12 = listOf("screen12.gif")
val session_screen12 = utterance {
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +"In a tug of war, each side exerts a force on the rope."
    +delay(500)
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +"If the forces are equal and opposite, they are balanced.The rope does not move."
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +"On the other hand, if the forces are unbalanced, like shown in the picture, the rope moves in the direction of the larger force. "
    +delay(500)
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +"Thus, an unbalanced force can cause a resting object to move. "
    +"It can also speed up, slow down, or change the direction of an already moving object. "
    +delay(2000)

}