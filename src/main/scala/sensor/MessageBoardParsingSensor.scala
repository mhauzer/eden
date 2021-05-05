package sensor

import cognition.{Fcu, Idea, Quale, SensoryType}
import communication.MessageBoardEntry
import nlp.NlpPattern

object NlpParser {
  private val Separator = "[ ,.\\-\\(\\);:\\[\\]!?]+"

  def parseLine(line: String): Array[Fcu] =
    for (token <- line.split(Separator)) yield
      Fcu(idea = Idea.WORD, quale = Quale(value = token, sense = SensoryType.TextPseudoVision))

  def parseLineWithPatterns(line: String): List[Fcu] = {
    var recognizedPatterns: List[NlpPattern] = Nil
    NlpPattern.patterns.foreach(_.reset())
    for (token <- line.split(Separator))
      for (pattern <- NlpPattern.patterns) {
        pattern.next(token)
        if (pattern.isRecognized)
          recognizedPatterns ::= pattern
      }
    if (recognizedPatterns.nonEmpty) recognizedPatterns.map(p => Fcu(idea = p.idea)) else Fcu(idea = Idea.UNKNOWN) :: Nil
  }
}

class MessageBoardParsingSensor(entry: MessageBoardEntry) extends Sensor[Fcu] {
  // we have to parse the entry here and convert it to a series of memes
  def get(): List[Fcu] = NlpParser.parseLine(entry.message).toList
}