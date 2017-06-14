package akka.http.actuate.health

import akka.actor.{Actor, Props}

case class GetHealth()

class HealthActor extends Actor {

  private def aggregateHealth(): Map[String, Health] = HealthIndicator.indicatorMap match  {
    case Some(indicators) => indicators.map { case (key, value) => (key, value.health) }
    case _ => Map()
  }

  private def aggregateStatus(checks: Map[String, Health]) = {
    if(checks.count { case (_, value) => value.status.code != Status.UP.code } == 0)
      Status.UP
    else
      Status.DOWN
  }

  def check(): Health = {
    val checks = aggregateHealth()
    val status = aggregateStatus(checks)

    Health(status, Some(checks))
  }

  def receive: Receive = {
    case GetHealth => sender() ! check()
  }
}

object HealthActor {
  val props: Props = Props[HealthActor]
}
