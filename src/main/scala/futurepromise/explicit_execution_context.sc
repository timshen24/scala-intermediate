import java.util.concurrent.Executors
import scala.concurrent.{ExecutionContext, Future}
import scala.util._

implicit val ec: ExecutionContext = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(4))
val aFuture = Future {
  42
}

aFuture.onComplete {
  case Success(value) => println(s"The async essence of life is $value")
  case Failure(exception) => println(s"Meaning of value failed $exception")
}

val bFuture = aFuture.map(_ + 1)