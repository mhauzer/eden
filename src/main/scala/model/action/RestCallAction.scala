package model.action

abstract class RestCallAction(aUrl: String) extends Action {
  val url: String = aUrl
}
