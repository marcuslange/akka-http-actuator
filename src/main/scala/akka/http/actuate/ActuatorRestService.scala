package akka.http.actuate

import akka.http.actuate.rest.{ActuatorLinks, HealthPort}
import akka.http.rest.hal.ResourceBuilder
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import spray.json.JsValue

trait ActuatorRestService extends ActuatorAdapter
  with HealthPort {

  val actuatorRoutes: Route =
    path("actuator") {
      complete(actuatorResource)
    } ~
    path("auditevents") {
      complete(actuatorResource)
    } ~
    path("autoconfig") {
      complete(actuatorResource)
    } ~
    path("beans") {
      complete(actuatorResource)
    } ~
    path("configprops") {
      complete(actuatorResource)
    } ~
    path("dump") {
      complete(actuatorResource)
    } ~
    path("env") {
      complete(actuatorResource)
    } ~
    path("flyway") {
      complete(actuatorResource)
    } ~
    healthRoute ~
    path("info") {
      complete(actuatorResource)
    } ~
    path("loggers") {
      complete(actuatorResource)
    } ~
    path("liquibase") {
      complete(actuatorResource)
    } ~
    path("metrics") {
      complete(actuatorResource)
    } ~
    path("mappings") {
      complete(actuatorResource)
    } ~
    path("shutdown") {
      complete(actuatorResource)
    } ~
    path("trace") {
      complete(actuatorResource)
    }
}

trait ActuatorAdapter {
  protected def actuatorResource: JsValue = {
    ResourceBuilder(
      withLinks = Some(Map(
        ActuatorLinks.actuatorLink("self"),
        ActuatorLinks.auditeventsLink("auditevents"),
        ActuatorLinks.autoconfigLink("autoconfig"),
        ActuatorLinks.beansLink("beans"),
        ActuatorLinks.configpropsLink("configprops"),
        ActuatorLinks.dumpLink("dump"),
        ActuatorLinks.envLink("env"),
        ActuatorLinks.flywayLink("flyway"),
        ActuatorLinks.healthLink("health"),
        ActuatorLinks.infoLink("info"),
        ActuatorLinks.loggersLink("loggers"),
        ActuatorLinks.liquibaseLink("liquibase"),
        ActuatorLinks.metricsLink("metrics"),
        ActuatorLinks.mappingsLink("mappings"),
        ActuatorLinks.shutdownLink("shutdown"),
        ActuatorLinks.traceLink("trace")
      ))
    ).build
  }

  protected def auditeventsResource: JsValue = {
    ResourceBuilder(
      withLinks = Some(Map(
        ActuatorLinks.auditeventsLink("self"),
        ActuatorLinks.actuatorLink("parent")
      ))
    ).build
  }

  protected def autoconfigResource: JsValue = {
    ResourceBuilder(
      withLinks = Some(Map(
        ActuatorLinks.autoconfigLink("self"),
        ActuatorLinks.actuatorLink("parent")
      ))
    ).build
  }

  protected def beansResource: JsValue = {
    ResourceBuilder(
      withLinks = Some(Map(
        ActuatorLinks.beansLink("self"),
        ActuatorLinks.actuatorLink("parent")
      ))
    ).build
  }

  protected def configpropsResource: JsValue = {
    ResourceBuilder(
      withLinks = Some(Map(
        ActuatorLinks.configpropsLink("self"),
        ActuatorLinks.actuatorLink("parent")
      ))
    ).build
  }

  protected def dumpResource: JsValue = {
    ResourceBuilder(
      withLinks = Some(Map(
        ActuatorLinks.dumpLink("self"),
        ActuatorLinks.actuatorLink("parent")
      ))
    ).build
  }

  protected def envResource: JsValue = {
    ResourceBuilder(
      withLinks = Some(Map(
        ActuatorLinks.envLink("self"),
        ActuatorLinks.actuatorLink("parent")
      ))
    ).build
  }

  protected def flywayResource: JsValue = {
    ResourceBuilder(
      withLinks = Some(Map(
        ActuatorLinks.flywayLink("self"),
        ActuatorLinks.actuatorLink("parent")
      ))
    ).build
  }

  protected def infoResource: JsValue = {
    ResourceBuilder(
      withLinks = Some(Map(
        ActuatorLinks.infoLink("self"),
        ActuatorLinks.actuatorLink("parent")
      ))
    ).build
  }

  protected def loggersResource: JsValue = {
    ResourceBuilder(
      withLinks = Some(Map(
        ActuatorLinks.loggersLink("self"),
        ActuatorLinks.actuatorLink("parent")
      ))
    ).build
  }

  protected def liquibaseResource: JsValue = {
    ResourceBuilder(
      withLinks = Some(Map(
        ActuatorLinks.liquibaseLink("self"),
        ActuatorLinks.actuatorLink("parent")
      ))
    ).build
  }

  protected def metricsResource: JsValue = {
    ResourceBuilder(
      withLinks = Some(Map(
        ActuatorLinks.metricsLink("self"),
        ActuatorLinks.actuatorLink("parent")
      ))
    ).build
  }

  protected def mappingsResource: JsValue = {
    ResourceBuilder(
      withLinks = Some(Map(
        ActuatorLinks.mappingsLink("self"),
        ActuatorLinks.actuatorLink("parent")
      ))
    ).build
  }

  protected def shutdownResource: JsValue = {
    ResourceBuilder(
      withLinks = Some(Map(
        ActuatorLinks.shutdownLink("self"),
        ActuatorLinks.actuatorLink("parent")
      ))
    ).build
  }

  protected def traceResource: JsValue = {
    ResourceBuilder(
      withLinks = Some(Map(
        ActuatorLinks.traceLink("self"),
        ActuatorLinks.actuatorLink("parent")
      ))
    ).build
  }
}