package sensor

import communication.MessageBoardEntry
import model.Skill
import model.Skill.Skill
import nlp.Lexicon

case class NlpPattern(skill: Skill, pattern: List[String]) {
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

object SkillParser {
  val patterns = List(
    NlpPattern(Skill.DATE, "data" :: Nil),
    NlpPattern(Skill.DATE, "dzień" :: Nil),
    NlpPattern(Skill.DEMO, "demo" :: Nil),
    NlpPattern(Skill.NAME, "imię" :: Nil),
    NlpPattern(Skill.QUIT, "dupa" :: Nil),
    NlpPattern(Skill.QUIT, "koniec" :: Nil),
    NlpPattern(Skill.QUIT, "q" :: Nil),
    NlpPattern(Skill.TIME, "czas" :: Nil),
    NlpPattern(Skill.TIME, "godzina" :: Nil),
    NlpPattern(Skill.TIME2, "kiedy" :: "być" :: "?" :: Nil),
    NlpPattern(Skill.VERSION, "wersja" :: Nil),
    NlpPattern(Skill.WEATHER, "pogoda" :: Nil),
    NlpPattern(Skill.WEATHER, "aura" :: Nil)
  )

  def parseLine(line: String): List[Skill] = {
    var recognizedPatterns: List[NlpPattern] = Nil
    patterns.foreach(_.reset())
    for (token <- line.split(Separator))
      for (pattern <- patterns) {
        pattern.next(token)
        if (pattern.isRecognized) {
          recognizedPatterns = pattern :: recognizedPatterns
        }
      }
    if (recognizedPatterns.nonEmpty) recognizedPatterns.map(_.skill) else Skill.NOTHING :: Nil
  }

  private val Separator = "[ ,.\\-\\(\\);:\\[\\]!?]+"
}



abstract class Sensor[SignalType] {
  def get(): List[SignalType]
}

class MessageBoardSensor(entry: MessageBoardEntry) extends Sensor[Skill] {
  // we have to parse the entry here and convert it to a series of memes
  def get(): List[Skill] = SkillParser.parseLine(entry.message)
}
