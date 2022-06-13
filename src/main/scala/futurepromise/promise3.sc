import scala.concurrent.*
import scala.concurrent.duration._
import scala.util.*

//ExecutionContext is required so Scala knows where to get the threads from
//This will be implicitly added to all Promises/Futures
import scala.concurrent.ExecutionContext.Implicits.global

val promise = Promise[String] //The promise will hold a string

promise.future.onComplete { result =>
  result match
    case Success(str) => println(str)
    case Failure(exception) => throw exception
}



//A future tells the System to spawn a new thread and run the code block inside it
Await.ready(Future{
  Thread sleep 1000
  promise success "You've just completed the promise with me in it!"
}, 2.second)

Thread sleep 1000





