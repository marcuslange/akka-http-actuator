package akka.http.actuator

import akka.http.rest.hal.HalBrowserService
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.Http

object Application extends App with AkkaImplicits
  with HalBrowserService
  with ActuatorRestService {
  val routes = halBrowserRoutes ~ actuatorRoutes
  val bindingFuture = Http().bindAndHandle(routes, "localhost", 8080)
}
