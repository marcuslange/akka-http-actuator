package akka.http.actuate.health

import org.scalatest.{Matchers, WordSpec}

import java.io.File

class DiskSpaceHealthIndicatorSpec extends WordSpec with Matchers {
  "Disk Space Health Indicator" should {

    "return a health with status of UP if the the space is within the threshold" in {
      val health = DiskSpaceHealthIndicator(new File("."), 2L).health
      health.status should be(Status.UP)
    }

    "return a health with status of DOWN if the the space exceeds the threshold" in {
      val health = DiskSpaceHealthIndicator(new File("."), 2199023255552L).health
      health.status should be(Status.DOWN)
    }

    "return a health with details of total, free, and threshold, " in {
      val details = DiskSpaceHealthIndicator(new File("."), 2199023255552L).health.details.get
      details should contain key "total"
      details should contain key "free"
      details should contain key "threshold"
    }

    "return a health entry for the indicator map when entry is called" in {
      val indicators = Map(DiskSpaceHealthIndicator.entry)
      indicators should contain ("diskSpace" -> DiskSpaceHealthIndicator())
    }
  }
}
