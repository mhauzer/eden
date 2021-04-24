import communication.{SimpleMessageBoard, MessageBoardEntry}
import service.Shell

import scala.annotation.tailrec
import scala.io.StdIn.readLine

object Eden extends App {
  val shell = new Shell

  @tailrec
  def interact(messageBoard: SimpleMessageBoard): Boolean = {
    if (messageBoard.contents.nonEmpty) {
      println(messageBoard.contents.head.message)
      if (messageBoard.quit) return false
    }
    val entry = new MessageBoardEntry("User", readLine(">"))
    if (entry.nonQuit)
      interact(messageBoard.append(shell.iterate(entry)))
    else
      false
  }

  interact(new SimpleMessageBoard(Nil))
}
