package akka.http.actuate.health

import java.io.File
import akka.http.actuate.AkkaHttpActuatorContext

case class DiskSpaceHealthIndicator(
  path:File = new File(AkkaHttpActuatorContext.config.getString("health.diskspace.path")),
  threshold:Long = AkkaHttpActuatorContext.config.getLong("health.diskspace.threshold")
) extends HealthIndicator {

  def health: Health = {
    val diskFreeInBytes = path.getFreeSpace

    val details = Some(Map(
      "total" -> path.getTotalSpace,
      "free" -> diskFreeInBytes,
      "threshold" -> threshold))

    if (diskFreeInBytes >= threshold)
      Health(Status.UP, details)
    else
      Health(Status.DOWN, details)
  }
}

object DiskSpaceHealthIndicator {
  val entry: (String, DiskSpaceHealthIndicator) = ("diskSpace", DiskSpaceHealthIndicator())
}