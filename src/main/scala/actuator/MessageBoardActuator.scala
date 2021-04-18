package actuator

import communication.{SimpleMessageBoard, MessageBoardEntry}
import model.Skill
import model.Skill.Skill

abstract class Actuator[SignalType, OutputType] {
  def act(memes: List[SignalType]): OutputType
}

class MessageBoardActuator extends Actuator[Skill, MessageBoardEntry] {
  private def translate(meme: Skill): String =
    meme match {
      case Skill.ADAM => "Adam"
      case Skill.CAROLINE => "Karolina"
      case Skill.DATE => "17 kwietnia 2021"
      case Skill.DEMO => "Można ze mną rozmawiać o wszystkim!"
      case Skill.ECHO => "echo"
      case Skill.EVE => "Ewa"
      case Skill.MARTIN => "Marcin"
      case Skill.MACIEJ => "Maciej"
      case Skill.NAME => "Tajemnica"
      case Skill.NOTHING => "hmm"
      case Skill.VERSION => "Wersja 0.1"
      case Skill.WEATHER => "Zapowiada się piękna pogoda"
      case Skill.QUIT => ":q"
      case Skill.TASK => "Nie masz nic do roboty"
      case Skill.TIME => "Nie mam zegarka"
      case Skill.TIME2 => "Nie wiem"
      case _ => "Nie rozumiem"
    }

  def act(memes: List[Skill]): MessageBoardEntry = new MessageBoardEntry(translate(Skill.EVE), translate(memes.head))
}
