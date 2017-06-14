package akka.http.actuate.health

import akka.http.actuate.rest.HealthPort
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpec}

class HealthPortSpec extends WordSpec with Matchers with ScalatestRouteTest {
  case class FakeIndicator(health:Health) extends HealthIndicator
  val fakeIndicatorUp = FakeIndicator(Health(Status.UP, Some(Map("ping"-> "1"))))
  val fakeIndicatorDown = FakeIndicator(Health(Status.DOWN, Some(Map("ping"-> "2", "pong" -> 3, "zing" -> 200L))))
  object HealthPortFixture extends HealthPort
  val healthRoutes: Route = HealthPortFixture.healthRoute

  "The Health Rest Port" should {

    "return the overall health status when /health is called" in {
      Get("/health") ~> healthRoutes ~> check {
        responseAs[String] should include("\"status\":\"UP\"")
      }
    }

    "return include a self link when /health is called" in {
      Get("/health") ~> healthRoutes ~> check {
        responseAs[String] should include("\"self\":{\"href\":\"/health\"}")
      }
    }

    "return include a parent link when /health is called" in {
      Get("/health") ~> healthRoutes ~> check {
        responseAs[String] should include("\"parent\":{\"href\":\"/actuator\"}")
      }
    }

    "return child health status when /health is called" in {
      HealthIndicator.indicators(Map("fake" -> fakeIndicatorUp))
      Get("/health") ~> healthRoutes ~> check {
        responseAs[String] should include("\"fake\":{\"status\":\"UP\",\"ping\":\"1\"}")
      }
    }

    "return DOWN if child is DOWN when /health is called" in {
      HealthIndicator.indicators(Map("faked" -> fakeIndicatorDown, "fakeu" -> fakeIndicatorUp))
      Get("/health") ~> healthRoutes ~> check {
        responseAs[String] should include("\"status\":\"DOWN\"")
      }
    }
  }
}