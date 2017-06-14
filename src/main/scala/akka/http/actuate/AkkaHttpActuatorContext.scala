package akka.http.actuate

import com.typesafe.config.{Config, ConfigFactory}

object AkkaHttpActuatorContext {
  val config: Config = {
    val config: Config = ConfigFactory.load
    config.checkValid(ConfigFactory.defaultReference(), "akka.http.actuator")
    config.getConfig("akka.http.actuator")
  }
}