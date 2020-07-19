package furhatos.app.furgui

import furhatos.nlu.*
import furhatos.nlu.grammar.Grammar
import furhatos.nlu.kotlin.grammar
import furhatos.nlu.common.Number
import furhatos.util.Language


class True : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("True", "Correct", "right", "that is correct", "that is right")
    }
}

class False : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("False", "Incorrect", "wrong", "that is not correct", "that is incorrect", "that is wrong", "that is not right")
    }
}
