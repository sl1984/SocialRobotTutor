package furhatos.app.furgui.lecture

import furhatos.flow.kotlin.utterance
import furhatos.records.Location

//
val sl1 = listOf("sl1.gif")
val session2_sl1 = utterance {
    +"We now know that an unbalanced  force will lead to a non resultant force acting on an object, "
    +"which will in turn cause the object to accelerate."
    +delay(2000)
}

//
val sl2 = listOf("sl2.gif")
val session2_sl2 = utterance {
    +"So, a natural question arises? How much does the object accelerate. Well, this is dependant on two "
    +"factors , the amount of resultant force and the mass of the object"
    +delay(2000)
}

//
val sl3 = listOf("sl3.gif")
val session2_sl3 = utterance {
    +"We learnt in newton’s first law that a resultant force is required to bring about an acceleration on an "
    +"object. The net force needed is proportional to the mass of the object, that you are trying to accelerate."
    +glance(Location.RIGHT, 5000)
    +delay(1000)
    +"These observations can be expressed using Newton’s second Law. Newton’s second Law"
    +" states that the resultant force is equal to mass times acceleration.  Now let’s try and understand"
    +" what we learnt with an example."
    +delay(2000)
}

//
val sl4 = listOf("sl4.gif")
val session2_sl4 = utterance {
    +"The picture shows two objects of equal masses."
    +glance(Location.RIGHT, 5000)
    +delay(1000)
    +" On the first object, we apply a force of 50 newton, "
    +"and on the second object a 25 newton force is  applied. Both objects are experiencing a resultant"
    +" force, can you guess which object will experience an increased acceleration."
    +delay(2000)
}

//
val sl5 = listOf("sl5.gif")
val session2_sl5 = utterance {
    +"From Newton’s second law, we now know that the acceleration is proportional to the resultant force acting"
    +" on the object.  What does this mean?"
    +delay(1000)
    +" It means that , if we have a greater force, then we have a greater acceleration."
    +"So the first object will experience twice the amount of acceleration of 2 meters per"
    +" second square, as compared to the second object which accelerates at 1 meter per second square."
    +delay(2000)
}

//
val sl6 = listOf("sl6.gif")
val session2_sl6 = utterance {
    +"Now, lets try to understand the relationship between mass and acceleration. In this screen, you can"
    +" observe two objects. "
    +glance(Location.RIGHT, 5000)
    +delay(1000)
    +"Both of them experience the same amount of force. The top object has a mass"
    +" of 25 kilograms, where as the bottom object has a mass of 10 kilogram. Which object do you think will"
    +" experience an increased acceleration?"
    +delay(2000)
}

//
val sl7 = listOf("sl7.gif")
val session2_sl7 = utterance {
    +"From Newton’s second law, we now know that , the acceleration is inversely proportional to the mass of the object."
    +delay(1000)
    +" In other words, if the mass is larger, the acceleration will be smaller. So, in this "
    +"case, the bottom object will experience twice the acceleration experienced by the top object."
    +delay(2000)
}

//
val sl8 = listOf("sl8.gif")
val session2_sl8 = utterance {
    +"As seen in Newtons second law, In order to calculate the force required to accelerate an object, we use"
    +" the equation, force equals mass times acceleration, where force is measured in newtons, mass in "
    +"kilograms and acceleration in meters per second square. "
    +delay(1000)
    +"For example, we would have to exert 50 newton"
    +" force in order for an object of 25 kilograms to experience an acceleration of 2 meters per second square"
    +delay(3000)
}

val fl27 = listOf("fl27.gif")
val session_fl27 = utterance {
    +"Now, lets take some time to reflect upon what we learnt so far."
    +glance(Location.RIGHT, 8000)
    +delay(1000)
    +"As you can see in the diagram, when forces are balanced, the resultant force is zero."
    +delay(1000)
    +"Therefore, an object maintains its state of motion.  Objects at rest, will continue to be at rest."
    +delay(1000)
    +"Similarly, objects in motion will carry on with constant motion."
    +delay(10000)
}

val fl28 = listOf("fl28.gif")
val session_fl28 = utterance {
    +"On the other hand, when forces are unbalanced, there is a resultant force"
    +glance(Location.RIGHT, 8000)
    +delay(1000)
    +"This resultant force causes acceleration."
    +delay(1000)
    +"If the objects are at rest, they may start moving."
    +delay(1000)
    +"If the objects are already in motion, they may speed up, slow down, stop or change direction based on the magnitude and direction of the resultant force. "
    +delay(1000)
    +"The acceleration produced is directly proportional to the force applied and inversely proportional to the mass of the object. "
    +delay(10000)
}

//
val thanks = listOf("thanks.gif")
val session2_thanks = utterance {
    +"That's the end of this lecture series on Newton's Laws of Motion"
    +"Hope you have enjoyed this session."
    +delay(1000)
    +"When you are ready, click the Quiz button to launch the practice tests."
    +delay(1000)
    +"We can make it more interactive and fun experience."
    +delay(2000)
}