package actuator

import communication.{MessageBoardEntry, SimpleMessageBoard}
import cognition.Idea
import Idea.Idea

abstract class Actuator[SignalType, OutputType] {
  def act(memes: List[SignalType]): OutputType
}

class MessageBoardActuator extends Actuator[Idea, MessageBoardEntry] {
  private def translate(idea: Idea): String =
    idea match {
      case Idea.UNKNOWN => "Nie wiem"
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
      case Idea.TIME => "Nie mam zegarka"
      case Idea.TIME2 => "Nie wiem"
      case Idea.DEMO => "Można ze mną rozmawiać o wszystkim!"
      case Idea.ECHO => "echo"
      case Idea.QUIT => ":q"
      case Idea.TASK => "Nie masz nic do roboty"
      case Idea.VERSION => "Wersja 0.1"
      case Idea.WEATHER => "Zapowiada się piękna pogoda"
      case _ => "Nie rozumiem"
    }

  def act(ideas: List[Idea]): MessageBoardEntry = new MessageBoardEntry(translate(Idea.EVE), translate(ideas.head))
}
