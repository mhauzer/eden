package model.action

class NameAction(name: String) extends Action {
  override def perform(input: String): String = name
}
