package nlp

// https://parentingpatch.com/sentence-purpose-declarative-interrogative-imperative-exclamatory/
// http://www.butte.edu/departments/cas/tipsheets/grammar/sentence_type.html
object SentencePurposes extends Enumeration {
  type SentencePurpose = Value

  val Unknown, Declarative, Interrogative, Imperative, Exclamatory = Value

  def fromChar(c: Char): SentencePurpose = {
    c match {
      case '.' => Declarative
      case '?' => Interrogative
      case '!' => Exclamatory
      case _ => Unknown
    }
  }
}
