package furhatos.app.furgui.lecture

import furhatos.flow.kotlin.utterance
import furhatos.records.Location

// send first law screen 1
val fl1a = listOf("fl1a.gif")
val session_fl1a = utterance {
    +"So, lets look at what Newtons Law states."
    +attend(Location.RIGHT)
    +glance(Location.RIGHT)
    +delay(1000)
    +"According to Newton’s First Law, "
    +"an object remains in the same state of motion, unless a resultant force acts on it."
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +delay(2000)
}

val fl1b = listOf("fl1b.gif")
val session_fl1b = utterance {
    +"First of all, we need to understand what a state of motion is. It could mean one of two things."
    +delay(2000)
}

val fl1c = listOf("fl1c.gif")
val session_fl1c = utterance {
    +" A state of motion could be something as simple as being stationary, like an object placed on a ground."
    +attend(Location.RIGHT)
    +glance(Location.RIGHT, 5000)
    +delay(1000)
    +" Or, it could mean that the object, is moving at a constant velocity. By constant velocity, "
    +"we are talking about the object, moving at the same speed, and, in the same direction.  "
    +delay(1000)
    +"An example of constant velocity, would be, that of a cyclist  moving at a 2 metres per second, "
    +"in the same direction."
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +delay(2000)
}

val fl1d = listOf("fl1d.gif")
val session_fl1d = utterance {
    +"Secondly, we also need to understand what resultant force means. There are multiple forces that can act on an object such as forces of friction, tension, thrust etc."
    +delay(1000)
    +"If we take the sum of all the forces acting on a body, we will get the net force or resultant force."
    +"If this net force is zero, then we  say that the forces are balanced."
    +delay(1000)
    +"On the other hand, if the sum of all the forces acting on an object are not equal to zero, "
    +"then we say that the forces are unbalanced. "
    +delay(2000)
}

val fl1e = listOf("fl1e.gif")
val session_fl1e = utterance {
    +"If the resultant force acting on a stationary object is zero, then the object will remain stationary."
    +attend(Location.RIGHT)
    +glance(Location.RIGHT, 5000)
    +delay(1000)
    +"For example, this is a book placed on a table. The table exerts an upward push towards the book. "
    +"Its weight is balanced by the equal and opposite reaction force."
    +delay(1000)
    +"Since both these forces balance each other, the net resultant force is zero. "
    +"Therefore, the book will continue to be stationary."
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +delay(2000)
}

val fl1f = listOf("fl1f.gif")
val session_fl1f = utterance {
    +"Similarly, if the resultant force acting on a moving object is zero, then the object will "
    +"continue moving in the same direction with the same speed, or in other words, with the same velocity."
    +attend(Location.RIGHT)
    +glance(Location.RIGHT, 5000)
    +delay(1000)
    +"This is an elevator, moving upwards with constant velocity. Can you locate the two forces acting on this elevator?"
    +delay(1000)
    +"The force of gravity is acting downwards. There is also a tension force from the cable acting upwards."
    +delay(1000)
    +"When these two forces are equal and acting in opposite direction,"
    +"the elevator moves  with constant velocity."
    +glance(Location.STRAIGHT_AHEAD)
    +attend(Location.STRAIGHT_AHEAD)
    +delay(2000)
}

// First Law Stationary Object - Zero Force
val fl4 = listOf("fl4.gif")
val session_fl4 = utterance {
    +"Let’s elaborate it a little more."
    +glance(Location.RIGHT, 3000)
    +"Consider this object. There are no forces acting on it currently and therefore it is stationary."
    +delay(2000)
}

val fl5 = listOf("fl5.gif")
val session_fl5 = utterance {
    +"Now, imagine there a 50 Newton force acting on its right, and a 50 Newton Force acting on its left."
    +glance(Location.RIGHT, 5000)
    +delay(1000)
    +"In this case, since the two forces exerted on the red object, are equal and opposite in direction,  "
    +"the forces are said to be balanced. So, then this object continues to remains stationary. "
    +delay(2000)
}

// First Law Moving Object
val fl6 = listOf("fl6.gif")
val session_fl6 = utterance {
    +"What if the object was moving at a constant velocity, say two meters per second?"
    +delay(2000)
}

// First Law Moving Object
val fl7 = listOf("fl7.gif")
val session_fl7 = utterance {
    +"Now we apply a 50 newton force on its right and another 50 newton force on its left.  "
    +glance(Location.RIGHT, 5000)
    +delay(1000)
    +"Once again the forces are balanced and the resultant force is zero. So, the object continues"
    +" to maintain its state."
    +delay(2000)
}

// First Law Moving Object
val fl8 = listOf("fl8.gif")
val session_fl8 = utterance {
    +"It will carry on moving with the same speed of two meters per second in the same direction"
    +delay(2000)
}

// First Law - Moving Car
val fl9a = listOf("fl9a.gif")
val session_fl9a = utterance {
    +"Here, is an example, of the forces acting on a stationary car."
    +glance(Location.RIGHT, 5000)
    +delay(1000)
    +"As you can see, the only two forces acting on this car, is the force of gravity downwards, "
    +delay(1000)
    +"and the equal and opposite reactive normal force upwards. "
    +delay(1000)
    +"Both these forces, are equal and opposite and  hence, balance each other, creating a zero resultant force."
    +"Thus, the car will continue to remain stationary. "
    +delay(2000)
}

// First Law - Moving Car
val fl9 = listOf("fl9.gif")
val session_fl9 = utterance {
    +"Let’s take a look at a car moving at a constant velocity of ten meters per second."
    +glance(Location.RIGHT, 8000)
    +delay(1000)
    +"It has two additional forces, compared to that of the stationary car. "
    +delay(1000)
    +"You can see that the driving force of the engine is acting towards the right. Because the car is moving "
    +"at a constant velocity, there should be an equal force acting in the opposite direction. "
    +"This is a natural force that occurs called the resistive force. It includes both friction "
    +"with the air and friction with the road."
    +delay(2000)
}

val fl10 = listOf("fl10.gif")
val session_fl10 = utterance {
    +"So, lets summarize Newton’s first law . An object at rest remains at rest, or if in motion, remains in "
    +"motion at a constant velocity unless acted on by a resultant force."
    +delay(3000)
}

// First Law - Non-Zero
val fl11 = listOf("fl11.gif")
val session_fl11 = utterance {
    +"From Newton’s first law, we can infer that  the velocity of an object will only change if a resultant"
    +" force is acting on it. Look at the figure. "
    +glance(Location.RIGHT, 8000)
    +delay(1000)
    +"When two forces acting on an object are not equal in size, we say that they are unbalanced forces. "
    +"The overall force acting on the object is called  the resultant force. "
    +delay(1000)
    +"If the forces are balanced, the resultant force is zero."
    +" In the slides to follow, we will be discussing how resultant forces affects the motion of an object."
    +delay(3000)
}

// First Law - Non-Zero
val fl12a = listOf("fl12a.gif")
val session_fl12a = utterance {
    +"Let’s look at an example. This is a stationary object."
    +delay(2000)
}

// First Law - Non-Zero
val fl12 = listOf("fl12.gif")
val session_fl12 = utterance {
    +"If you apply  fifteen newton force to this object, towards the right direction "
    +delay(2000)
}

// First Law - Non-Zero
val fl13 = listOf("fl13.gif")
val session_fl13 = utterance {
    +"and five newton to the left direction, then it will result in an unbalanced force that leads to "
    +"a net resultant force acting on the object"
    +delay(2000)
}

// First Law - Non-Zero
val fl14 = listOf("fl14.gif")
val session_fl14 = utterance {
    +glance(Location.RIGHT, 5000)
    +"This resultant force causes the object to accelerate to the right. "
    +delay(1000)
    "That was pretty straight forward, since the object was initially in stationary position. "
    +"Now, depending on the initial state of the object, the resultant "
    +"force can cause different things happening to the object"
    +delay(3000)
}

// First Law - Non-Zero
val fl15 = listOf("fl15.gif")
val session_fl15 = utterance {
    +"For example, if the object was already moving in the right direction at two meters per second "
    +delay(1000)
}

// First Law - Non-Zero
val fl16 = listOf("fl16.gif")
val session_fl16 = utterance {
    +"Then a resultant force applied on it towards the right direction"
    +delay(1000)
}

// First Law - Non-Zero
val fl17 = listOf("fl17.gif")
val session_fl17 = utterance {
    +"will cause it to, speed up, or, in other words, accelerate, towards the right"
    +delay(3000)
}

// First Law - Non-Zero
val fl18 = listOf("fl18.gif")
val session_fl18 = utterance {
    +"What if this object was moving towards the left at two meters per second?"
    +delay(1000)
}

// First Law - Non-Zero
val fl19 = listOf("fl19.gif")
val session_fl19 = utterance {
    +"Now, if you add a resultant force in the opposite direction towards the right."
    +delay(1000)
}

// First Law - Non-Zero
val fl20 = listOf("fl20.gif")
val session_fl20 = utterance {
    +glance(Location.RIGHT, 3000)
    +"Then, the object will slow down to, say, one meter per second."
    +delay(3000)
}

// First Law - Non-Zero
val fl21 = listOf("fl21.gif")
val session_fl21 = utterance {
    +"What if the object was moving towards the left slowly?"
    +delay(1000)
}

// First Law - Non-Zero
val fl22 = listOf("fl22.gif")
val session_fl22 = utterance {
    +"Then, a resultant force acting on the opposite direction"
    +delay(1000)
}

// First Law - Non-Zero
val fl23 = listOf("fl23.gif")
val session_fl23 = utterance {
    +"may cause it to stop."
    +delay(3000)
}

// First Law - Non-Zero
val fl24 = listOf("fl24.gif")
val session_fl24 = utterance {
    +"In all the cases we discussed so far, if you notice, the acceleration caused by the unbalanced force,"
    +" resulted in the object, to either start moving, speed up, slow down or stop."
    +delay(2000)
}

// First Law - Non-Zero
val fl25 = listOf("fl25.gif")
val session_fl25 = utterance {
    +"But what if the acceleration caused by the unbalanced force"
    +"only made the object change its direction and continue in the same speed."
    +delay(1000)
}

// First Law - Non-Zero
val fl26 = listOf("fl26.gif")
val session_fl26 = utterance {
    +glance(Location.RIGHT, 5000)
    +"The object was initially moving to the right direction at two meters per second. A resultant force is"
    +" acting on it, in the upward direction. The object changes its direction, but continues with the same"
    +" speed of two meters per second"
    +delay(3000)
}

// send screen
val fl_text1 = listOf("fl_text1.gif")
val session_fl_text1 = utterance {
    +"Technically, acceleration is defined, as the change in velocity, over time."
    +delay(2000)
}

// send screen
val fl_text2 = listOf("fl_text2.gif")
val session_fl_text2 = utterance {
    +glance(Location.RIGHT, 3000)
    +"Velocity is determined by, both speed and direction. So, any change in direction of the object, "
    +"also changes the velocity, and hence, causes acceleration."
    +delay(3000)
}

// send session_fl_planet - Earth moving around sun
val fl_planet = listOf("fl_planet.gif")
val session_fl_planet = utterance {
    +"A good example of this is circular motion. "
    +glance(Location.RIGHT, 5000)
    +delay(1000)
    +"Consider the earth, revolving around the sun in a circular motion."
    +"Even though, the speed may remain constant, but the direction keeps"
    +" changing. Therefore, the velocity keeps changing and causes it to accelerate."
    +delay(3000)
}