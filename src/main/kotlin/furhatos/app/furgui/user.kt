package furhatos.app.furgui

import furhatos.flow.kotlin.UserDataDelegate
import furhatos.records.Record
import furhatos.records.User

var User.registrationId : String? by UserDataDelegate()

// User variables
class QuizPerformanceData(
        var questionId : String = "",
        var isAnsCorrect : Boolean = true,
        var promptHelp1 : Boolean = false,
        var promptHelp2: Boolean = false,
        var help1 : Boolean = false,
        var help2: Boolean = false,
        var noUserResponse: Boolean = false,
        var duration : Int = 0,
        var comments : MutableList<String> = mutableListOf()
) : Record()

val User.quiz : QuizPerformanceData
    get() = data.getOrPut(QuizPerformanceData::class.qualifiedName, QuizPerformanceData())