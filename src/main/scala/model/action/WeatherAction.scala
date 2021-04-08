package model.action

import java.io.IOException

class WeatherAction(url: String) extends RestCallAction(url) {
  override def perform(input: String): String = {
    val output = "Muszę się tego nauczyć"
/*
    var output = new String
    val restTemplate = new RestTemplate

    val url = "http://api.openweathermap.org/data/2.5/weather?q=Wrocław&units=Metric&APIkey=0c5cdd2e1c08df83b6acc3ed70734cb1"
    val response = restTemplate.getForEntity(url, classOf[String])

    if (response.getStatusCode ne HttpStatus.OK) output = "Błąd podczas komunikacji z serwisem pogodowym"
    else {
      val mapper = new ObjectMapper
      try {
        val weather = mapper.readValue(response.getBody, classOf[WeatherResponse])
        output = weather.getName + ": " + weather.getWeather.get(0).getDescription + " " + weather.getMain.getTemp
      } catch {
        case e: IOException =>
          output = "Nie rozumiem odpowiedzi z serwisu pogodowego: " + e.getMessage
      }
    }
*/

    output
  }
}
