package furhatos.app.furgui.lecture

import furhatos.flow.kotlin.utterance
import furhatos.gestures.Gesture
import furhatos.gestures.Gestures
import furhatos.records.Location

val screen1 = listOf("screen1.gif")
val session_screen1 = utterance {
    +"Let’s get started."
    +Gestures.BigSmile
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +delay(1000)
    +"Have you ever played the tug of war game?"
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +delay(2000)
}
val session_screen1_yes = utterance {
    +Gestures.Nod(10.0, 2000.0)
    +"Great! So, then, let me try and explain how force works, using tug of war as an example"
    +Gestures.Smile(5.0, 1000.0)
    +delay(2000)
}

val session_screen1_no = utterance {
    +Gestures.Surprise
    +"Okay. Lets find out another example for you!"
    +delay(2000)
}

// Tug of War Example
val screen2 = listOf("screen2.gif")
val session_screen2 = utterance {
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +delay(1000)
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +"I am sure you would  have experienced a push or a pull while playing tug of war."
    +delay(1000)
    +"Force is defined as a push or pull that acts on an object."
    +delay(2000)
}

val screen3 = listOf("screen3.gif")
val session_screen3 = utterance {
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +delay(1000)
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +"When you play tug of war, you may have come across a situation, where, one team is pulling the other team towards them."
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +delay(1000)
    +"This is because the magnitude of force exerted by  the first team is higher, "
    +"and the net force is acting towards the direction team exerting the larger force"
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +delay(2000)
}

val screen4 = listOf("screen4.gif")
val session_screen4 = utterance {
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +delay(1000)
    +"Forces come in pairs. In every interaction, there is a pair of forces acting on the two interacting objects."
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +" The magnitude of forces on the first object equals, the magnitude of forces on the second object."
    +delay(2000)
}

val screen4a = listOf("screen4a.gif")
val session_screen4a = utterance {
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +" Now, I  want you  to focus on Person B."
    +delay(1000)
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +delay(1000)
    +"I would like to show you all the forces acting on Person B."
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +"A person standing on ground, experiences the force of gravity."
    +"Can you see a force of gravity acting downwards in the picture? "
    +delay(1000)
    +"This downward weight of gravity, is balanced by the upward push of the ground called the reactive force,"
    +" as shown in green. These two forces are equal and opposite."
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +delay(1000)
    +"When person A pulls the rope, there is a tension exerted on Person B. This force  tries to pull"
    +" him towards Person A."
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +delay(1000)
    +"Finally, there is a force of friction, caused by the person sliding against the ground."
    +"This acts towards the opposite direction of tension."
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +delay(2000)
}

// Tug Of War review
val session_screen4a_review = utterance {
    +Gestures.Thoughtful(10.0, 5000.0)
    +"If there is something you don’t understand, that’s fine, we can go through another example."
    +delay(1000)
    +"Would you like me to show you a simpler example?"
    +Gestures.Smile(1.0, 2000.0)
}

val session_screen4a_yes = utterance {
    +Gestures.Smile(1.0, 2000.0)
    +"Sure. I'm happy to explain the concept of paired nature of forces, using a simpler example"
}

// Box on Table Example
val screen5 = listOf("screen5.gif")
val session_screen5 = utterance {
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +"In this picture a box is at rest on a table. It experiences a pair of forces, equal and opposite."
    +delay(1000)
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
    +delay(1000)
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +"And the other is the opposite  reaction force upwards. "
    +delay(2000)
}

val screen7 = listOf("screen7.gif")
val session_screen7 = utterance {
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +delay(1000)
    +"Take a look at this picture."
    +delay(1000)
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +"Now, if you apply some force and tilt the table, the box starts sliding down. "
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +delay(1000)
    +"Apart from the force of gravity and reaction force, a new force is experienced by the box."
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +delay(2000)
}

val screen8 = listOf("screen8.gif")
val session_screen8 = utterance {
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +delay(1000)
    +"This is the force of friction."
    +delay(1000)
    +"It is experienced when two objects slide past each other."
    +delay(1000)
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +"Since the box is sliding downwards, friction will act exactly in its opposite direction."
    +delay(2000)
}

val screen9 = listOf("screen9.gif")
val session_screen9 = utterance {
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +delay(1000)
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +"Now imagine that, this box is hung on ceiling using a rope."
    +delay(1000)
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +delay(1000)
    +"As you can see there, is a force of gravity acting downwards."
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +"There is also another force that comes into play here. What could this be?"
    +delay(2000)
}

val screen10 = listOf("screen10.gif")
val session_screen10 = utterance {
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +"An object that is being stretched experiences a tension force."
    +delay(1000)
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +"This will act exactly in the opposite direction of the gravitational force which is pulling the box downwards"
    +delay(2000)
}

//Quiz
val quiz_screen1 = listOf("quiz_screen1.gif")
val session_quiz_screen1 = utterance {
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +"Now, can you go ahead and try to answer this question?"
    +delay(1000)
    +"If an object is not moving, we can conclude that there are no forces acting it."
    +delay(1000)
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +"Is this statement true?"
}

val quiz_screen2 = listOf("quiz_screen2.gif")
val session_quiz_screen2 = utterance {
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +"We learnt that forces can be both balanced and unbalanced."
    +delay(1000)
    +"What happens when the forces on both sides of the tug of war are balanced?"
    +delay(1000)
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
}

val quiz_screen3 = listOf("quiz_screen3.gif")
val session_quiz_screen3 = utterance {
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +"Well done!"
    +delay(1000)
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +"In fact, we could have said its true, it if  balanced forces was mentioned instead of no forces."
    +delay(1000)
    +"Congratulations, you have successfully completed the session on force and motion."
    +delay(1000)
}

val screen11 = listOf("screen11.gif")
val session_screen11 = utterance {
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +delay(1000)
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +"Now, Are you able to understand this more clearly?"
}

val screen12 = listOf("screen12.gif")
val session_screen12 = utterance {
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +"In a tug of war, each side exerts a force on the rope."
    +delay(1000)
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +"If the forces are equal, they are balanced, and the rope does not move."
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +"On the other hand, if the forces are unbalanced, like shown in the picture, the rope moves in the direction of the larger force. "
    +delay(1000)
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +"Thus, an unbalanced force can cause a resting object to move. "
    +"It can also speed up, slow down, or change the direction of an already moving object. "
    +delay(2000)

}