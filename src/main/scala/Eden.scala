import scala.io.StdIn.readLine
import service.ActuatorService

object Eden extends App {
  val gender = if (args.length == 1) args(0) else "f"
  val actuator = new ActuatorService(gender)

  var end = false    
  while (!end) {      
    val userSentence = readLine(">")      
    end = (userSentence == "Do widzenia") || (userSentence == "q")
    println(actuator.act(userSentence))
  }
}