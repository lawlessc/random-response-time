package test

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class WaitSimulation extends Simulation {
	val httpConf = http.baseURL("http://localhost:9001")
	.acceptCharsetHeader("ISO-8859-1,utf-8;q=0.7,*;q=0.7")
	.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
	.acceptEncodingHeader("gzip, deflate")
	.acceptLanguageHeader("fr,fr-fr;q=0.8,en-us;q=0.5,en;q=0.3")
	
	val scn = scenario("user-repeat").repeat(100){exec(http("1").get("/wait"))}
		
	setUp(
		scn.inject(rampUsers(100) over (20 seconds))
		).protocols(httpConf)
}