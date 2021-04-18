package service

import actuator.MessageBoardActuator
import communication.MessageBoardEntry
import model.Skill
import model.Skill._
import sensor.MessageBoardSensor

/*
  ShellService is a shell of an entity that is capable of communicating with the outer world through the following
  senses and actuators:
    - senses:
      1. MessageBoardSensor
    - actuators:
      1. MessageBoardActuator
 */
class ShellService(gender: String) {
  private val personService = new PersonService(gender)

  // reaction types:
  // - reflex based on a simple association (meme patterns associated with other meme patterns)
  def react(inputMemes: List[Skill]): List[Skill] = {
    if (inputMemes.head == Skill.NAME)
      (if (personService.gender == "f") Skill.EVE else Skill.ADAM) :: Nil
    else
      inputMemes
  }

  def iterate(input: MessageBoardEntry): MessageBoardEntry = {
    new MessageBoardActuator().act(react(new MessageBoardSensor(input).get()))
  }
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


//  private val nlpService = null

//  private val weatherService = null

/*
skills.put(Skill.TASK, new TaskManagerAction("http://localhost:8080/api/"))
wordmap.put("zadanie", Skill.TASK)
 */

//  private def understand(input: String) = { //  SkillInfo = {
//    val sentence = new Sentence(input)
//
//    val firstWord = sentence.words.head.toLowerCase
//
//   // if (wordmap.keys.exists(_ == firstWord)) wordmap(firstWord) else Skill.ECHO
//    Skill.ECHO
//  }

//  def act(input: String): String = {
//    new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-20")
//    val message = understand(input)
//   // skills(message.skill).perform(message.args)
//    ""
//  }

