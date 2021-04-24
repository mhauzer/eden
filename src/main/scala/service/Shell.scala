package service

import actuator.MessageBoardActuator
import cognition.WorkingMemory
import communication.MessageBoardEntry
import sensor.MessageBoardSensor

/*
  ShellService is a shell of an entity that is capable of communicating with the outer world through the following
  senses and actuators:
    - senses:
      1. MessageBoardSensor
    - actuators:
      1. MessageBoardActuator
 */
class Shell {
  def iterate(input: MessageBoardEntry): MessageBoardEntry =
    new MessageBoardActuator().act(
      new WorkingMemory(
        new MessageBoardSensor(input).get()
      ).process()
    )
}

//  private val skills = Map(
//    Skill.DATE -> new CurrentDateAction,
//    Skill.DEMO -> new DemoAction,
//    Skill.ECHO -> new EchoAction(gender),
//    Skill.NAME -> new NameAction(personService.person.name),
//    Skill.NOTHING -> new DoNothingAction(),
//    Skill.QUIT -> new QuitAction(),
//    Skill.TIME -> new TimeAction,
//    Skill.TIME2 -> {},
//    Skill.VERSION -> new VersionAction,
//    Skill.WEATHER -> new WeatherAction("http://api.openweathermap.org/data/2.5/weather?q=Wrocław&units=Metric&APIkey=0c5cdd2e1c08df83b6acc3ed70734cb1")
//  )
