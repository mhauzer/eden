package actuator

import cognition.{Fcu, Idea, Quale, SensoryType, WorkingMemory}
import cognition.value.Sequence
import communication.MessageBoardEntry

abstract class Actuator[WorkingMemory, OutputType] {
  def act(workingMemory: WorkingMemory): OutputType
}

class MessageBoardActuator extends Actuator[WorkingMemory, MessageBoardEntry] {
  private def translate(fcu: Fcu): String =
    fcu.idea match {
      case Idea.UNKNOWN => "nie wiem"
      case Idea.GREETINGS => "cześć"
      case Idea.GENDER => "płeć"
      case Idea.FEMALE => "kobieta"
      case Idea.MALE => "mężczyzna"
      case Idea.NAME => "imię"
      case Idea.ANN => "Anna"
      case Idea.EVE => "Ewa"
      case Idea.CAROLINE => "Karolina"
      case Idea.ADAM => "Adam"
      case Idea.MARTIN => "Marcin"
      case Idea.MACIEJ => "Maciej"
      case Idea.NOTHING => "nic"
      case Idea.SOMETHING => "coś"
      case Idea.DATE => "17 kwietnia 2021"
      case Idea.TIME => "nie mam zegarka"
      case Idea.TIME2 => "nie wiem"
      case Idea.DEMO => "Można ze mną rozmawiać o wszystkim!"
      case Idea.ECHO => "echo"
      case Idea.QUIT => ":q"
      case Idea.TASK => "nie masz nic do roboty"
      case Idea.VERSION => "wersja 0.1"
      case Idea.WEATHER => "zapowiada się piękna pogoda"
      case _ => "nie rozumiem"
    }

  def act(workingMemory: WorkingMemory): MessageBoardEntry = {
    new MessageBoardEntry(
      translate(Fcu(new Sequence(1), Idea.EVE, Quale(0, Quale.VolumeMedium, Quale.VarianceMedium, WorkingMemory.ReflexTtlThreshold, "Ewa", SensoryType.TextPseudoVision), Nil)),
      workingMemory.fcus.filter(_.ttl >= WorkingMemory.ReflexTtlThreshold).map(translate).mkString(" ")
    )
  }
}
