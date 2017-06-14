package akka.http.actuator.rest

import akka.actor.ActorRef
import akka.http.actuator.AkkaImplicits
import akka.http.actuator.health.{GetHealth, Health, HealthActor, Status}
import akka.http.rest.hal.ResourceBuilder
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives.{complete, path}
import akka.http.scaladsl.server.Route
import akka.pattern.ask
import spray.json._

trait HealthAdapter extends DefaultJsonProtocol {

  protected def healthResource(health:Health): JsValue = {
    ResourceBuilder(
      withData = Some(mapHealth(health).toJson),
      withLinks = Some(Map(
        ActuatorLinks.healthLink("self"),
        ActuatorLinks.actuatorLink("parent")
      ))
    ).build
  }

  private def mapHealth(health:Health):Map[String, JsValue] = {
    val mappedDetails = health.details match {
      case Some(hDetails) => mapDetails(hDetails)
      case _ => Map()
    }

    Map("status" -> health.status.code.toJson) ++ mappedDetails
  }

  private def mapDetails(details:Map[String, AnyRef]):Map[String, JsValue] = details.map {
    case (key, det:Health) => (key, mapHealth(det).toJson)
    case (key, det:String) => (key, det.toJson)
    case (key, det:AnyRef) => (key, det.toString.toJson)
    case (key, _) => (key, "Could not serialize detail".toJson)
  }
}

trait HealthPort extends AkkaImplicits with HealthAdapter with SprayJsonSupport {

  private val healthService: ActorRef = system.actorOf(HealthActor.props)

  val healthRoute: Route =
    path("health") {
      complete {
        (healthService ? GetHealth).mapTo[Health].map(h => healthResource(h))
      }
    }
}
