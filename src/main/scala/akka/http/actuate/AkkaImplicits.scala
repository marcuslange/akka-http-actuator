package akka.http.actuate

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.util.Timeout

import scala.concurrent.ExecutionContextExecutor
import scala.concurrent.duration._

trait AkkaImplicits {
  implicit val timeout:Timeout = 1 minute
  implicit val system = ActorSystem("akka-http-actuator")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher
}
