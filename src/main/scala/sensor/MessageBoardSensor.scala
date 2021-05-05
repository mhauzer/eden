package sensor

import cognition.value.Ttl
import cognition.{Fcu, Idea, Quale, SensoryType}
import communication.MessageBoardEntry

// in fact it looks rather like a cognitive tokenizer than parser but we'll see how it is in the future
object CognitiveParser {
  private val defaultStreamId: Byte = 0

  def parseLine(line: String): List[Fcu] =
    if (line.isEmpty) Nil
    else
      Fcu(
        idea = Idea.QUALE,
        quale = new Quale(
          defaultStreamId,
          Quale.VolumeMedium,
          Quale.DensityMedium,
          if (line.head.isWhitespace) Quale.VarianceLow else Quale.VarianceMedium,
          line.head.toString,
          SensoryType.TextPseudoVision
        )
      ) :: Nil ::: parseLine(if (line.length > 1) line.substring(1) else "")
}

class MessageBoardSensor(entry: MessageBoardEntry) extends Sensor[Fcu] {
  // we have to parse the entry here and convert it to a series of memes
  def get(): List[Fcu] = CognitiveParser.parseLine(entry.message)
}
