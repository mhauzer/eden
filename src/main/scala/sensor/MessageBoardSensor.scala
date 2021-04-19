package sensor

import communication.MessageBoardEntry
import cognition.{Fcu, Idea, Quale, TextPseudoVisionQuale}
import Idea.Idea
import nlp.Lexicon

case class NlpPattern(idea: Idea, pattern: List[String]) {
  private var args: List[String] = Nil
  private var position: Integer = -1

  def reset(): List[String] = {
    position = -1
    args.empty
  }

  def isRecognized: Boolean = position == pattern.length - 1

  def next(token: String): Boolean = {
    if (pattern(position + 1) == "?") {
      args = (token :: args).reverse
      true
    } else if (pattern(position + 1) == Lexicon.getBaseform(token)) {
      position = position + 1
      true
    } else
      false
  }
}

// in fact it looks rather like a cognitive tokenizer than parser but we'll see how it is in the future
object CognitiveParser {
//  val patterns = List(
//    NlpPattern(Idea.UNKNOWN, "nie" :: "wiedzieć" :: Nil),
//    NlpPattern(Idea.UNKNOWN, "nie" :: "znać" :: Nil),
//    NlpPattern(Idea.GENDER, "płeć" :: Nil),
//    NlpPattern(Idea.FEMALE, "kobieta" :: Nil),
//    NlpPattern(Idea.MALE, "mężczyzna" :: Nil),
//    NlpPattern(Idea.DATE, "data" :: Nil),
//    NlpPattern(Idea.DATE, "dzień" :: Nil),
//    NlpPattern(Idea.DEMO, "demo" :: Nil),
//    NlpPattern(Idea.NAME, "imię" :: Nil),
//    NlpPattern(Idea.ANN, "Anna" :: Nil),
//    NlpPattern(Idea.EVE, "Ewa" :: Nil),
//    NlpPattern(Idea.CAROLINE, "Karolina" :: Nil),
//    NlpPattern(Idea.ADAM, "Adam" :: Nil),
//    NlpPattern(Idea.MARTIN, "Marcin" :: Nil),
//    NlpPattern(Idea.MACIEJ, "Maciej" :: Nil),
//    NlpPattern(Idea.NOTHING, "nic" :: Nil),
//    NlpPattern(Idea.SOMETHING, "coś" :: Nil),
//    NlpPattern(Idea.QUIT, "dupa" :: Nil),
//    NlpPattern(Idea.QUIT, "koniec" :: Nil),
//    NlpPattern(Idea.QUIT, "q" :: Nil),
//    NlpPattern(Idea.TIME, "czas" :: Nil),
//    NlpPattern(Idea.TIME, "godzina" :: Nil),
//    NlpPattern(Idea.TIME2, "kiedy" :: "być" :: "?" :: Nil),
//    NlpPattern(Idea.VERSION, "wersja" :: Nil),
//    NlpPattern(Idea.WEATHER, "pogoda" :: Nil),
//    NlpPattern(Idea.WEATHER, "aura" :: Nil)
//  )

  def parseLine(line: String): List[Fcu] = {
    val DefaultTtl: Byte = 3

    (for (token <- line.split(Separator)) yield
      Fcu(Idea.ENTITY, new TextPseudoVisionQuale(token, 0, Quale.Medium, Quale.Medium, DefaultTtl), Nil, DefaultTtl)).toList

    //    var recognizedPatterns: List[NlpPattern] = Nil
//    patterns.foreach(_.reset())
//    for (token <- line.split(Separator))
//      for (pattern <- patterns) {
//        pattern.next(token)
//        if (pattern.isRecognized) {
//          recognizedPatterns = pattern :: recognizedPatterns
//        }
//      }
//    if (recognizedPatterns.nonEmpty) recognizedPatterns.map(_.idea) else Idea.UNKNOWN :: Nil
  }

  private val Separator = "[ ,.\\-\\(\\);:\\[\\]!?]+"
}


// Sensor is an internal representation of a sense. This is not a "real" sense. The real sense should be set before
// the sensor. Sensor generetes experiences (qualia) that are subjects of cognition.
abstract class Sensor[SignalType] {
  def get(): List[SignalType]
}

class MessageBoardSensor(entry: MessageBoardEntry) extends Sensor[Fcu] {
  // we have to parse the entry here and convert it to a series of memes
  def get(): List[Fcu] = CognitiveParser.parseLine(entry.message)
}
