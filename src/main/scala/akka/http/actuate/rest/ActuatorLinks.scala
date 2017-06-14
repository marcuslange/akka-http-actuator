package akka.http.actuate.rest

import akka.http.rest.hal.Link

object ActuatorLinks {
  def actuatorLink(rel:String): (String, Link) = rel -> Link(href = "/actuator")
  def auditeventsLink(rel:String): (String, Link) = rel -> Link(href = "/auditevents")
  def autoconfigLink(rel:String): (String, Link) = rel -> Link(href = "/autoconfig")
  def beansLink(rel:String): (String, Link) = rel -> Link(href = "/beans")
  def configpropsLink(rel:String): (String, Link) = rel -> Link(href = "/configprops")
  def dumpLink(rel:String): (String, Link) = rel -> Link(href = "/dump")
  def envLink(rel:String): (String, Link) = rel -> Link(href = "/env")
  def flywayLink(rel:String): (String, Link) = rel -> Link(href = "/flyway")
  def healthLink(rel:String): (String, Link) = rel -> Link(href = "/health")
  def infoLink(rel:String): (String, Link) = rel -> Link(href = "/info")
  def loggersLink(rel:String): (String, Link) = rel -> Link(href = "/loggers")
  def liquibaseLink(rel:String): (String, Link) = rel -> Link(href = "/liquibase")
  def metricsLink(rel:String): (String, Link) = rel -> Link(href = "/metrics")
  def mappingsLink(rel:String): (String, Link) = rel -> Link(href = "/mappings")
  def shutdownLink(rel:String): (String, Link) = rel -> Link(href = "/shutdown")
  def traceLink(rel:String): (String, Link) = rel -> Link(href = "/trace")
}
