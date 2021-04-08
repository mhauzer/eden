package model.action

import model.{Lexicon, Sentence, SentencePurposes}

class DemoAction extends Action {
  override def perform(input: String): String = {
    val butterfly = Map("number" -> "plural", "case" -> "C")
    val month = Map("number" -> "plural", "case" -> "vocative")

    val sentences = List(
      new Sentence(
        Lexicon.getAdjectiveForm("ulotny", Lexicon.getNoun("chwila").gender, butterfly("number"), butterfly("case"))
          :: Lexicon.getNounForm("chwila", butterfly("number"), butterfly("case"))
          :: "jak"
          :: Lexicon.getNounForm("motyl", butterfly("number"), butterfly("case"))
          :: Nil,
        SentencePurposes.Declarative
      ),
      new Sentence(
        Lexicon.getVerbForm("witać", month("number"))
          :: Lexicon.getNounForm("kwiecień", month("number"), month("case"))
          :: Nil,
        SentencePurposes.Exclamatory
      )
    )
    sentences.mkString("\n")
  }

}
