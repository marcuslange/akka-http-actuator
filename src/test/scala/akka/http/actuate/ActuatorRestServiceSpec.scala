package akka.http.actuate

import akka.http.scaladsl.server.Route
import org.scalatest.{Matchers, WordSpec}
import akka.http.scaladsl.testkit.ScalatestRouteTest

class ActuatorRestServiceSpec extends WordSpec with Matchers with ScalatestRouteTest {
  object ActuatorRestServiceFixture extends ActuatorRestService
  val actuatorRoutes: Route = ActuatorRestServiceFixture.actuatorRoutes

  "The Actuator Service" should {

    "return the actuator entry with all links when /actuator is called" in {
      Get("/actuator") ~> actuatorRoutes ~> check {
        responseAs[String] shouldEqual "{\"_links\":{\"health\":{\"href\":\"/health\"},\"autoconfig\":{\"href\":\"/autoconfig\"},\"mappings\":{\"href\":\"/mappings\"},\"beans\":{\"href\":\"/beans\"},\"trace\":{\"href\":\"/trace\"},\"configprops\":{\"href\":\"/configprops\"},\"flyway\":{\"href\":\"/flyway\"},\"self\":{\"href\":\"/actuator\"},\"dump\":{\"href\":\"/dump\"},\"info\":{\"href\":\"/info\"},\"shutdown\":{\"href\":\"/shutdown\"},\"liquibase\":{\"href\":\"/liquibase\"},\"loggers\":{\"href\":\"/loggers\"},\"metrics\":{\"href\":\"/metrics\"},\"auditevents\":{\"href\":\"/auditevents\"},\"env\":{\"href\":\"/env\"}}}"
      }
    }
  }
}