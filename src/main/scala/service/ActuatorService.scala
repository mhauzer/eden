package service

import model.{Sentence, Skill}
import model.action.{DateAction, DemoAction, DoNothingAction, EchoAction, NameAction, QuitAction, Time2Action, TimeAction, VersionAction, WeatherAction}

class ActuatorService(gender: String) {
  private val personService = new PersonService(gender)

  private val skills = Map(
    Skill.DATE -> new DateAction,
    Skill.DEMO -> new DemoAction,
    Skill.ECHO -> new EchoAction(gender),
    Skill.NAME -> new NameAction(personService.person.name),
    Skill.NOTHING -> new DoNothingAction(),
    Skill.QUIT -> new QuitAction(),
    Skill.TIME -> new TimeAction,
    Skill.TIME2 -> new Time2Action,
    Skill.VERSION -> new VersionAction,
    Skill.WEATHER -> new WeatherAction("http://api.openweathermap.org/data/2.5/weather?q=Wrocław&units=Metric&APIkey=0c5cdd2e1c08df83b6acc3ed70734cb1")
  )
  private val wordmap = Map(
    "data" -> Skill.DATE,
    "dzień" -> Skill.DATE,
    "" -> Skill.NOTHING,
    "demo" -> Skill.DEMO,
    "imię" -> Skill.NAME,
    "koniec" -> Skill.QUIT,
    "q" -> Skill.QUIT,
    "czas" -> Skill.TIME,
    "godzina" -> Skill.TIME,
    "czas2" -> Skill.TIME2,
    "wersja" -> Skill.VERSION,
    "pogoda" -> Skill.WEATHER,
    "aura" -> Skill.WEATHER
  )

//  private val nlpService = null

//  private val weatherService = null

    /*
    skills.put(Skill.TASK, new TaskManagerAction("http://localhost:8080/api/"))
    wordmap.put("zadanie", Skill.TASK)
     */

  private def understand(input: String) = {
    val sentence = new Sentence(input)

    val firstWord = sentence.words.head.toLowerCase

    if (wordmap.keys.exists(_ == firstWord)) wordmap(firstWord) else Skill.ECHO
  }

  def act(input: String): String = {
    skills(understand(input)).perform(input)
  }
}
