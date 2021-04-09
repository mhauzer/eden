import scala.io.StdIn.readLine
import service.ActuatorService

object Eden extends App {
  def interact(actuator: ActuatorService): Boolean = {
    val response = actuator.act(readLine(">"))
    println(response)
    if (response != "Do zobaczenia")
      interact(actuator)
    true
  }

  interact(new ActuatorService(if (args.length == 1) args(0) else "f"))
}