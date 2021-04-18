package cognition

// https://link.springer.com/article/10.1007/s12559-017-9538-5
object Idea extends Enumeration {
  type Idea = Value
  val
    UNKNOWN,
    WORD,
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
