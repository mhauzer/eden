package nlp

class Phrase(words: List[String]) {
  override def toString: String = (words.head.capitalize :: words.tail).reduceLeft(_ + " " + _)
}

class Sentence(val words: List[String], purpose: SentencePurposes.SentencePurpose) {
  def this(ws: List[String]) {
    this (ws, if (ws != "" :: Nil) SentencePurposes.fromChar(ws.last.last) else SentencePurposes.Unknown)
  }

  def this(s: String) = {
    this(s.split(" +").toList)
  }

  override def toString: String = (words.head.capitalize :: words.tail).reduceLeft(_ + " " + _) + Sentence.punctuation(purpose)
}

object Sentence {
  def punctuation(purpose: SentencePurposes.SentencePurpose): String =
    purpose match {
      case SentencePurposes.Declarative => "."
      case SentencePurposes.Imperative => "."
      case SentencePurposes.Interrogative => "?"
      case SentencePurposes.Exclamatory => "!"
    }
}
