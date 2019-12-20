package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class PauseTime extends Simulation {

  val httpConf = http.baseUrl("http://video-game-db.eu-west-2.elasticbeanstalk.com/swagger-ui/index.html")
    .header("Accept", "application/json")

  val scn = scenario("Video Game DB - Different Types of Pause")

    // Type 1 - Pause for a fixed duration - defaults to seconds
    .exec(http("Get all video games - 1st call")
      .get("videogames"))
    .pause(5)

    // Type 2 - Pause for a fixed duration, specify the time unit
    .exec(http("Get specific game - 1st call")
      .get("videogames/1"))
    .pause(4000.milliseconds)

    // Type 3 - Pause for a random time between two durations
    .exec(http("Get all Video games - 2nd call")
      .get("videogames"))
    .pause(1, 5)

    // Type 4 - Pause for a random time between two durations, specify the time unit
    .exec(http("Get specific game - 1st call")
      .get("videogames/1"))
    .pause(1000.milliseconds, 7000.milliseconds)

  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpConf)

}
