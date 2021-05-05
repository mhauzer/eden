import cognition.value.Ttl
import cognition.{Fcu, Idea, Quale, WorkingMemory}
import org.scalatest.PrivateMethodTester
import org.scalatest.funsuite.AnyFunSuite

import scala.collection.immutable.HashSet

// https://www.scalatest.org/user_guide/using_scalatest_with_sbt
class WorkingMemorySuite extends AnyFunSuite with PrivateMethodTester {
  private val qH = Quale(value = "h")
  private val qE = Quale(value = "e")
  private val qJ = Quale(value = "j")
  private val qHej = Quale(value = "hej")

  private val fcuQualeH = Fcu(1, Idea.QUALE, qH)
  private val fcuQualeE = Fcu(2, Idea.QUALE, qE)
  private val fcuQualeJ = Fcu(3, Idea.QUALE, qJ)

  private val fcuLetterH = Fcu(4, Idea.LETTER, qH, HashSet(fcuQualeH))
  private val fcuLetterE = Fcu(5, Idea.LETTER, qE, HashSet(fcuQualeE))
  private val fcuLetterJ = Fcu(6, Idea.LETTER, qJ, HashSet(fcuQualeJ))
  private val fcuWordHej = Fcu(7, Idea.WORD, qHej, HashSet(fcuLetterH, fcuLetterE, fcuLetterJ))

  private val qualeList = List(fcuQualeH, fcuQualeE, fcuQualeJ)

  private val degradedQualeList = List(
    Fcu(1, Idea.QUALE, qH, ttl = new Ttl(2)),
    Fcu(2, Idea.QUALE, qE, ttl = new Ttl(2)),
    Fcu(3, Idea.QUALE, qJ, ttl = new Ttl(2))
  )

  private val hejWord = Fcu(4, Idea.WORD, Quale(value = "hej"))
  private val swiecieWord = Fcu(2, Idea.WORD, Quale(value = "świecie"))

  private val reason = PrivateMethod[List[Fcu]](Symbol("reason"))
  private val degrade = PrivateMethod[List[Fcu]](Symbol("degrade"))

  test("taxonomize an empty list") {
    assertResult(List.empty)(WorkingMemory.taxonomize(List.empty[Fcu]))
  }

  test("taxonomize a single Quale to Letter") {
    assertResult(List(fcuLetterH))(WorkingMemory.taxonomize(List(fcuQualeH)))
  }

  test("taxonomize letters to word") {
    assertResult(List(fcuWordHej))(WorkingMemory.taxonomize(List(fcuLetterH, fcuLetterE, fcuLetterJ)))
  }

  test("degrade a list") {
    assertResult(degradedQualeList)(WorkingMemory invokePrivate degrade(qualeList))
  }

  test("reasoning on an empty FCU list gives an empty FCU list") {
    assertResult(Nil)(WorkingMemory invokePrivate reason(Nil, None, Nil))
  }

  test ("reasoning by inserting a complex element to en empty FCU list") {
    val result = List(hejWord)

    assertResult(result)(WorkingMemory invokePrivate reason(Nil, Option(hejWord), Nil))
  }

  test ("reasoning by inserting a complex element to a single element FCU list") {
    val result = List(hejWord, swiecieWord)

    assertResult(result)(WorkingMemory invokePrivate reason(List(hejWord), Option(swiecieWord), Nil))
  }

  test("reasoning on a single item FCU list") {
    val fcuList = List(Fcu())
    assertResult(fcuList)(WorkingMemory invokePrivate reason(fcuList, None, Nil))
  }

  test("empty WorkingMemory processing") {
    val workingMemory = new cognition.WorkingMemory(Nil)
    assertResult(workingMemory.content)(workingMemory.process().content)
  }

  test("WorkingMemory processing a single letter") {
    val workingMemory = new cognition.WorkingMemory(qualeList)
    assertResult(degradedQualeList)(workingMemory.process().content)
  }
}
