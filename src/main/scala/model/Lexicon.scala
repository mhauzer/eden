package model

import java.text.SimpleDateFormat
import java.util.Date

import scala.collection.immutable.HashMap
import scala.util.Random

// https://parentingpatch.com/sentence-purpose-declarative-interrogative-imperative-exclamatory/
// http://www.butte.edu/departments/cas/tipsheets/grammar/sentence_type.html
object SentencePurposes extends Enumeration {
  type SentencePurpose = Value
  
  val Declarative, Interrogative, Imperative, Exclamatory = Value
}


class Phrase(words: List[String]) {
  override def toString: String = (words.head.capitalize :: words.tail).reduceLeft(_ + " " + _)
}


class Sentence(words: List[String], purpose: SentencePurposes.SentencePurpose) {  
  override def toString: String = (words.head.capitalize :: words.tail).reduceLeft(_ + " " + _) + Sentence.punctuation(purpose)
}


object Sentence {
  def punctuation(purpose: SentencePurposes.SentencePurpose): String = 
    purpose match {
      case SentencePurposes.Declarative => "."
      case SentencePurposes.Imperative => "."
      case SentencePurposes.Interrogative => "?"
      case SentencePurposes.Exclamatory => "!"
    }
}

class Noun(g: String, singular: NounDeclension, plural: NounDeclension) {
  val gender: String = g
  def get(number: String): NounDeclension = 
    if (number == "singular") singular
    else if (number == "plural") plural
    else new NounDeclension("error: " + number + " not found!", "", "", "", "", "", "")
}

class NounDeclension(nominative: String, genitive: String, dative: String, accusative: String, ablative: String, locative: String, vocative: String) {  
  def get(`case`: String): String = 
    if (`case` == "nominative" || `case` == "mianownik" || `case` == "M") nominative
    else if (`case` == "genitive" || `case` == "dopełniacz" || `case` == "D") genitive
    else if (`case` == "dative" || `case` == "celownik" || `case` == "C") dative
    else if (`case` == "accusative" || `case` == "biernik" || `case` == "B") accusative
    else if (`case` == "ablative" || `case` == "narzędnik" || `case` == "N") ablative
    else if (`case` == "locative" || `case` == "miejscownik" || `case` == "Ms") locative
    else if (`case` == "vocative" || `case` == "wołacz" || `case` == "W") vocative
    else ""
  
  def help(lang: String): List[String] =
    if (lang == "pl")
      "mianownik" :: "(kto? co?)" :: "dopełniacz" :: "(kogo? czego?)" :: "celownik" :: "(komu? czemu?)" :: "biernik" :: "(kogo? co?)" :: "narzędnik" :: "(z kim? z czym?)" :: "miejscownik" :: "(o kim? o czym?)" :: "wołacz" :: "(o!)" :: Nil
    else
      "Help" :: "not found" :: Nil    
}


object Lexicon {
  private val nouns = Map(
    "chwila" -> new Noun(
      "f",
      new NounDeclension("chwila", "chwili", "chwili", "chwilę", "chwilą", "chwili", "chwilo"),
      new NounDeclension("chwile", "chwil", "chwilom", "chwile", "chwilami", "chwilach", "chwile")
    ),
    "czerwiec" -> new Noun(
      "m",
      new NounDeclension("czerwiec", "czerwca", "czerwcowi", "czerwiec", "czerwcem", "czerwcu", "czerwcu"),
      new NounDeclension("czerwce", "czerwców", "czerwcom", "czerwce", "czerwcami", "czerwcach", "czerwce")
    ),
    "motyl" -> new Noun(
      "m",
      new NounDeclension("motyl", "motyla", "motylowi", "motyla", "motylem", "motylu", "motylu"),
      new NounDeclension("motyle", "motyli", "motylom", "motyle", "motylami", "motylach", "motyle")
    ), 
    "czwartek" -> new Noun(
      "m",
      new NounDeclension("czwartek", "czwawrtku", "czwartkowi", "czwartek", "czwartkiem", "czwartku", "czwartku"),
      new NounDeclension("czwartki", "czwartków", "czwartkom", "czwartki", "czwartkami", "czwartkach", "czwartki")
    ),
    "dzień" -> new Noun(
      "m",
      new NounDeclension("dzień", "", "", "", "", "", ""),
      new NounDeclension("dni", "", "", "", "", "", "")
    ),
    "godzina" -> new Noun(
      "f",
      new NounDeclension("godzina", "", "", "", "", "", ""),
      new NounDeclension("godziny", "", "", "", "", "", "")
    ),
    "grudzień" -> new Noun(
      "m",
      new NounDeclension("grudzień", "", "", "", "", "", ""),
      new NounDeclension("grudnie", "", "", "", "", "", "")
    ),
    "herbata" -> new Noun(
      "f",
      new NounDeclension("herbata", "", "", "", "", "", ""),
      new NounDeclension("herbaty", "", "", "", "", "", "")
    ),
    "kwiecień" -> new Noun(
      "m",
      new NounDeclension("kwiecień", "kwietnia", "kwietniowi", "kwiecień", "kwietniem", "kwietniu", "kwietniu"),
      new NounDeclension("kwietnie", "kwietniów", "kwietniom", "kwietnie", "kwietniami", "kwietniach", "kwietnie")
    ),
    "lipiec" -> new Noun(
      "m",
      new NounDeclension("lipiec", "", "", "", "", "", ""),
      new NounDeclension("lipce", "", "", "", "", "", "")
    ),
    "listopad" -> new Noun(
      "m",
      new NounDeclension("listopad", "", "", "", "", "", ""),
      new NounDeclension("listopady", "", "", "", "", "", "")
    ),
    "luty" -> new Noun(
      "m",
      new NounDeclension("luty", "", "", "", "", "", ""),
      new NounDeclension("lute", "", "", "", "", "", "")
    ),
    "maj" -> new Noun(
      "m",
      new NounDeclension("maj", "", "", "", "", "", ""),
      new NounDeclension("maje", "", "", "", "", "", "")
    ),
    "marzec" -> new Noun(
      "m",
      new NounDeclension("marzec", "", "", "", "", "", ""),
      new NounDeclension("marce", "", "", "", "", "", "")
    ),
    "Michał" -> new Noun(
      "m",
      new NounDeclension("Michał", "", "", "", "", "", ""),
      new NounDeclension("Michały", "", "", "", "", "", "")
    ),
    "miesiąc" -> new Noun(
      "m",
      new NounDeclension("miesiąc", "miesiąca", "miesiącowi", "miesiąc", "miesiącem", "miesiącu", "miesiącu"),
      new NounDeclension("miesiące", "miesięcy", "miesiącom", "miesiące", "miesiącami", "miesiącach", "miesiące")
    ),
    "minuta" -> new Noun(
      "f",
      new NounDeclension("minuta", "", "", "", "", "", ""),
      new NounDeclension("minuty", "", "", "", "", "", "")
    ),
    "niedziela" -> new Noun(
      "f",
      new NounDeclension("niedziela", "", "", "", "", "", ""),
      new NounDeclension("niedziele", "", "", "", "", "", "")
    ),
    "październik" -> new Noun(
      "m",
      new NounDeclension("październik", "", "", "", "", "", ""),
      new NounDeclension("październiki", "", "", "", "", "", "")
    ),
    "piątek" -> new Noun(
      "m",
      new NounDeclension("piątek", "", "", "", "", "", ""),
      new NounDeclension("piątki", "", "", "", "", "", "")
    ),
    "poniedziałek" -> new Noun(
      "m",
      new NounDeclension("poniedziałek", "", "", "", "", "", ""),
      new NounDeclension("poniedziałki", "", "", "", "", "", "")
    ),
    "rok" -> new Noun(
      "m",
      new NounDeclension("rok", "roku", "rokowi", "rok", "rokiem", "roku", "roku"),
      new NounDeclension("lata", "lat", "latom", "lata", "latami", "latach", "lata")
    ),
    "sekunda" -> new Noun(
      "f",
      new NounDeclension("sekunda", "sekundy", "", "", "", "", ""),
      new NounDeclension("sekundy", "sekund", "", "", "", "", "")
    ),
    "sierpień" -> new Noun(
      "m",
      new NounDeclension("sierpnień", "", "", "", "", "", ""),
      new NounDeclension("sierpnie", "", "", "", "", "", "")
    ),
    "sobota" -> new Noun(
      "f",
      new NounDeclension("sobota", "", "", "", "", "", ""),
      new NounDeclension("soboty", "", "", "", "", "", "")
    ),
    "styczeń" -> new Noun(
      "m",
      new NounDeclension("styczeń", "", "", "", "", "", ""),
      new NounDeclension("stycznie", "", "", "", "", "", "")
    ),
    "środa" -> new Noun(
      "f",
      new NounDeclension("środa", "", "", "", "", "", ""),
      new NounDeclension("środy", "", "", "", "", "", "")
    ),
    "wrzesień" -> new Noun(
      "m",
      new NounDeclension("wrzesień", "", "", "", "", "", ""),
      new NounDeclension("wrześnie", "", "", "", "", "", "")
    ),
    "wtorek" -> new Noun(
      "m",
      new NounDeclension("wtorek", "", "", "", "", "", ""),
      new NounDeclension("wtorki", "", "", "", "", "", "")
    )
  )
  
  private val adjectives = Map(
    "dowolny" -> Map(
      "m" -> Map(
        "singular" -> new NounDeclension("dowolny", "", "", "", "", "", ""),
        "plural" -> new NounDeclension("dowolni", "", "", "", "", "", "")
      )
    ),
    "gorący" -> Map(
      "m" -> Map(
        "singular" -> new NounDeclension("gorący", "", "", "", "", "", ""),
        "plural" -> new NounDeclension("gorący", "", "", "", "", "", "")
      )    
    ),
    "ulotny" -> Map(
      "m" -> Map(
        "singular" -> new NounDeclension("ulotny", "", "", "", "", "", ""),
        "plural" -> new NounDeclension("ulotni", "", "", "", "", "", "")
      ), 
      "f" -> Map(
        "singular" -> new NounDeclension("ulotna", "ulotnej", "ulotnej", "ulotną", "ulotną", "ulotnej", "ulotna"),
        "plural" -> new NounDeclension("ulotne", "ulotnych", "ulotnym", "ulotne", "ulotnymi", "ulotnych", "ulotne")
      ), 
      "n" -> Map(
        "singular" -> new NounDeclension("ulotne", "", "", "", "", "", ""),
        "plural" -> new NounDeclension("ulotne", "", "", "", "", "", "")
      )
    )
  )
  
  private val verbs = Map(
    "witać" -> Map(
      "singular" -> "witaj",
      "plural" -> "witajcie"
    ),
    "usłyszeć" -> Map(
      "singular" -> "usłysz",
      "plural" -> "usłyszcie"
    )
  )
  
  private val pronoun_adverbs = Map(
    "jak" -> Map(
      "m" -> Map(
      ),
      "f" -> Map(
      ),
      "n" -> Map(
      )   
    )
  )

  private val names = Map(
    "f" -> Seq("Anna", "Ewa", "Karolina"),
    "m" -> Seq("Adam", "Marcin", "Maciek")
  )

  private val random = new Random
  
  def getNoun(baseform: String): Noun = nouns(baseform)
  def getNounForm(baseform: String, number: String, `case`: String): String = getNoun(baseform).get(number).get(`case`)
  def getAdjectiveForm(baseform: String, gender: String, number: String, `case`: String): String = adjectives(baseform)(gender)(number).get(`case`)
  def getVerbForm(baseform: String, number: String): String = verbs(baseform)(number)
  def getRandomName(gender: String): String = names(gender)(random.nextInt(names(gender).length))
}

object PhraseGenerator {
	val MINUTE_IN_SECONDS: Int = 60
	val HOUR_IN_SECONDS: Int = 60 * MINUTE_IN_SECONDS
	val DAY_IN_SECONDS: Int = 24 * HOUR_IN_SECONDS
	val MONTH_IN_SECONDS: Int = 30 * DAY_IN_SECONDS
	val YEAR_IN_SECONDS: Int = 365 * DAY_IN_SECONDS



  // https://alvinalexander.com/scala/scala-get-current-date-time-hour-calendar-example/
  def sayTemporalOrientation(distantTime: Date, currentTime: Date): String =
		if (distantTime.compareTo(currentTime) < 0) "przeszłość"
		else if (distantTime.compareTo(currentTime) > 0) "przyszłość"
		else "teraz"
    
    
	def determineNounCaseForNumber(number: Integer): HashMap[String, String] = {
		var nounNumber = if (number == 1) "singular" else "plural"
    var nounCase = ""
		
		// - przed, np. chwilą
		// -- przed, np.  chwilami
		// + po, np.  chwili
		// ++ po, np.  chwilach
		if (number == 0)
		{
			nounNumber = "singular"
			nounCase = "N"
		}
		else
		// common rule is if n % 10 == 0, 2, 3 or 4 you must use M case and otherwise D
		if  ((number % 10) == 2
			|| (number % 10) == 3
			|| (number % 10) == 4)
		  nounCase = "M"
		else
			nounCase = "D"

		// but there are exceptions:
		if (number == 1)
			nounCase = "B"
		else
		if ((number % 100) == 12
			|| (number % 100) == 13
			|| (number % 100) == 14)
			nounCase = "D"
			
		val result = HashMap("number" -> nounNumber, "case" -> nounCase)
    result
	}
    
    
    
	// pos: (part of sentence) S - subject, V - verb,  A - adverbial
	def sayTimeOfDay(hour: Int, pos: String = "S"): String = 
           if (hour == 0)  { if (pos == "A") "o północy" else "północ" }
		  else if (hour < 4)   { if (pos == "A") "w nocy" else "noc" }
		  else if (hour == 4)  { if (pos == "A") "nad ranem" else "nad ranem" }
		  else if (hour < 11)  { if (pos == "A") "rano" else "rano" }
		  else if (hour == 11) { if (pos == "A") "przed południem"	else "przed południem" }		
		  else if (hour == 12) { if (pos == "A") "w południe" else "południe" }			
		  else if (hour < 15)	 { if (pos == "A") "wczesnym popołudniem" else "wczesne popołudnie" }		
		  else if (hour < 17)  { if (pos == "A") "po południu" else "popołudnie" }
		  else if (hour < 18)  { if (pos == "A") "późnym popołudniem" else "późne popołudnie" }
		  else if (hour < 22)  { if (pos == "A") "wieczorem" else "wieczór"	}
		  else if (hour <= 24) { if (pos == "A") "w nocy" else "noc" }
      else "" 


 
 
 	// start - first time point (string)
	// end   - second time point (string)
	// orientation - past, now, future
	def sayHowLongAgoItWas(start: Date, end: Date, orientation: String = "past"): String = {
    var phrase = ""
    var unit = ""
    var number = 0L

    //println(s"start=$start, hours=${start.getHours()}, end=$end")
    
    var interval = diffTime(start, end)
		
    if (interval("totalSeconds") == -1)
			return "brak danych"

    //println(interval)
    
		if (interval("years") == 0 && interval("months") == 0 && interval("days") == 0 && interval("hours") <= 3)
    {
			if (interval("hours") > 0)
			{									
				number = interval("hours")
				unit   = "godzina"										
			}
			else if (interval("minutes") > 3)
			{
				number = interval("minutes")
				unit   = "minuta"
			}
			else
			{
				number = 0
				unit = "chwila"				
			}
    }
    else
    {
    	interval = diffTime(start, end, "days")
      		
      //println(interval)
              	
			if (interval("years") > 0)
			{
				number = interval("years")
				unit   = "rok"												
			} 
			else if (interval("months") > 0)
			{
				number = interval("months")
				unit   = "miesiąc"																							
			}
			else if (interval("days") > 20)
			{				
				phrase = if (orientation == "past") "3 tygodnie temu" else if (orientation == "future") "za 3 tygodnie" else "3 tygodnie"
			}
			else if (interval("days") > 13)
			{
				phrase = if (orientation == "past") "2 tygodnie temu" else if (orientation == "future") "za 2 tygodnie" else "2 tygodnie"
			}
			else if (interval("days") > 10)
			{
				phrase = if (orientation == "past") "prawie 2 tygodnie temu" else if (orientation == "future") "za niecałe 2 tygodnie" else "prawie 2 tygodnie"
			}				
			else if (interval("days") > 8)
			{
				phrase = if (orientation == "past") "ponad tydzień temu" else if (orientation == "future") "za ponad tydzień" else "ponad tydzień"
			}			
			else if (interval("days") > 6)
			{
				phrase = if (orientation == "past") "tydzień temu" else if (orientation == "future") "za tydzień" else "tydzień"
			}
			else if (interval("days") > 2)
			{
				phrase = if (orientation == "past") interval("days")+" dni temu" else (if (orientation == "future") "za " else "") + interval("days") + " dni"
			}
			else if (interval("days") == 2)
			{
				phrase = if (orientation == "past") "przedwczoraj" else if (orientation == "future") "pojutrze" else "2 dni"
			}			
			else if (interval("days") == 1)
			{
				phrase = if (orientation == "past") "wczoraj" else if (orientation == "future") "jutro" else "1 dzień"
			}
			else if (interval("days") == 0)
			{
				phrase = "dzisiaj"
			}
    }

    //println(s"phrase=$phrase")
    
    var expression = ""
    
		if (phrase.length == 0)
		{
			if (number > 6) 
			{
				expression = "ponad"
				phrase = expression + " " + phrase				
			}
				
			val nounInfo = determineNounCaseForNumber(number.toInt)
			
//			if (orientation == "future") 
//				nounInfo("case") = "B"
			
      // było '-'
			if (number != 0 && number > 1)
				phrase = number.toString
					
//      println(s"baseform=$unit, ${nounInfo("number")}, ${nounInfo("case")}")
			phrase = phrase + " " + Lexicon.getNounForm(unit, nounInfo("number"), nounInfo("case"))									
			
			if (number == 0)
			{
				phrase = (if (orientation == "past") "przed " else if (orientation == "future") "za " else "") + phrase
			}
			else      
				phrase = if (orientation == "past") s"$phrase temu" else s"za $phrase"
		}
		
		if (interval("totalDays") < 3 && interval("totalHours") >= 4)
			phrase = phrase + " " + sayTimeOfDay(start.getHours, "A")
    			
          
    phrase
  }
 
 
 
	// diffTime()
	// returns absolute interval between two time points
	// start - first time point (string)
	// end   - second time point (string)
	// accuracy  - accuracy: seconds (default), days	
	def diffTime(start: Date, end: Date, accuracy: String = "seconds"): Map[String, Long] = {
		var totalSeconds = -1L
		var totalHours   = -1L
		var totalDays    = -1L
		var years        = -1L
		var months		  = -1L
    var days		  = -1L
		var hours		  = -1L
		var minutes      = -1L
		var seconds      = -1L
		
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd")
    val startMidnight = start.clone().asInstanceOf[Date]
		val endMidnight   = end.clone().asInstanceOf[Date]

    if (accuracy == "days") {
      startMidnight.setHours(0)
      startMidnight.setMinutes(0)
      startMidnight.setSeconds(0)
      endMidnight.setHours(0)
      endMidnight.setMinutes(0)
      endMidnight.setSeconds(0)      
    }

    /*
    println(s"diffTime, $accuracy")	
    println(startMidnight)
    println(endMidnight)    
    println(endMidnight.getTime() / 1000)
    println(startMidnight.getTime() / 1000)
    */
    var interval = (endMidnight.getTime  / 1000 - startMidnight.getTime / 1000).abs
    totalSeconds = interval			
			
		totalHours   = totalSeconds / HOUR_IN_SECONDS
		totalDays    = totalSeconds / DAY_IN_SECONDS
			
		// count years		
		years = interval / YEAR_IN_SECONDS	
		interval = interval - years * YEAR_IN_SECONDS
			
		// count months
		months = interval / MONTH_IN_SECONDS
		interval = interval - months * MONTH_IN_SECONDS
			
		// count days
		days = interval / DAY_IN_SECONDS
		interval = interval - days * DAY_IN_SECONDS
			
		// count hours
		hours = interval / HOUR_IN_SECONDS
		interval = interval - hours * HOUR_IN_SECONDS		
			
		// count minutes
		minutes = interval / MINUTE_IN_SECONDS
		interval = interval - minutes * MINUTE_IN_SECONDS
			
		// cound seconds
		seconds = interval

	  val result = Map(
	    	"totalSeconds" -> totalSeconds,
	    	"totalHours" -> totalHours,
	    	"totalDays" -> totalDays,
	    	"years" -> years, "months" -> months, "days" -> days, "hours" -> hours, "minutes" -> minutes, "seconds" -> seconds
	  )
    result
	}
 
}