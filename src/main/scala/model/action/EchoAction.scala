package model.action

class EchoAction(gender: String) extends Action {
  override def perform(input: String): String =
    (if (gender == "f") "Usłyszałam" else "Usłyszałem") + " \"" + input + "\""
}
