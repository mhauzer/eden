package cognition

object Idea extends Enumeration {
  type Idea = Value
  val
    UNKNOWN,
    ENTITY,
    WORD,
    GREETINGS,
    GENDER,
      FEMALE,
      MALE,
    NAME,
      ANN, EVE, CAROLINE,
      ADAM, MARTIN, MACIEJ,
    NOTHING, SOMETHING,
    DATE, TIME, TIME2,
    DEMO, ECHO, QUIT, TASK, VERSION, WEATHER = Value
}
