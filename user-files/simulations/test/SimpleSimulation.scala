package test

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class SimpleSimulation extends Simulation {
	val httpConf = http.baseURL("https://www.google.ie")
	.acceptCharsetHeader("ISO-8859-1,utf-8;q=0.7,*;q=0.7")
	.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
	.acceptEncodingHeader("gzip, deflate")
	.acceptLanguageHeader("fr,fr-fr;q=0.8,en-us;q=0.5,en;q=0.3")
	
	val scn = scenario("SimpleSimulation")
	.exec(http("request_1")
	.get("/")
	)
	
	setUp(scn.inject(atOnceUsers(1))).protocols(httpConf)
}