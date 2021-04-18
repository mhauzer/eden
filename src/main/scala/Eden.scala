import communication.{SimpleMessageBoard, MessageBoardEntry}
import service.ShellService

import scala.annotation.tailrec
import scala.io.StdIn.readLine

object Eden extends App {
  val shell = new ShellService(if (args.length == 1) args(0) else "f")

  @tailrec
  def interact(messageBoard: SimpleMessageBoard): Boolean = {
    if (messageBoard.contents.nonEmpty) {
      println(messageBoard.contents.head.message)
      if (messageBoard.contents.head.message == ":q") return false
    }
    val entry = new MessageBoardEntry("User", readLine(">"))
    if (entry.message != ":q") {
      interact(messageBoard.append(shell.iterate(entry)))
    } else
      false
  }

  interact(new SimpleMessageBoard(Nil))
}
