package test

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class HelloSimulation extends Simulation {
	val httpConf = http.baseURL("http://localhost:9001")
	.acceptCharsetHeader("ISO-8859-1,utf-8;q=0.7,*;q=0.7")
	.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
	.acceptEncodingHeader("gzip, deflate")
	.acceptLanguageHeader("fr,fr-fr;q=0.8,en-us;q=0.5,en;q=0.3")

	
	val scn = scenario("HelloSimulation")
	.repeat(10){
		exec(http("1").get("/helloworld"))
	}
	
	setUp(
		scn.inject(
			atOnceUsers(10), 
			nothingFor(1 seconds), 
			atOnceUsers(20), 
			nothingFor(2 seconds),
			atOnceUsers(30), 
			nothingFor(3 seconds),
			atOnceUsers(40), 
			nothingFor(4 seconds),
			atOnceUsers(50),
			nothingFor(5 seconds),
			atOnceUsers(100),
			nothingFor(10 seconds),
			atOnceUsers(200)
		)).protocols(httpConf)
	
}