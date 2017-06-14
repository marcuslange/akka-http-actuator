package akka.http.actuate.health

object HealthIndicator {
  var indicatorMap: Option[Map[String, HealthIndicator]] = None
  def indicators(indicators:Map[String, HealthIndicator]): Unit = indicatorMap = Some(indicators)
}

trait HealthIndicator {
  def health: Health
}

case class Health(
  status:Status,
  details:Option[Map[String, Any]] = None) {
}

object Status {
  val UNKNOWN = new Status("UNKNOWN","")
  val UP = new Status("UP","")
  val DOWN = new Status("DOWN","")
  val OUT_OF_SERVICE = new Status("OUT_OF_SERVICE","")
}

case class Status(code: String, description: String)