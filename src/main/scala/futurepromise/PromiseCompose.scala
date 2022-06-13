package futurepromise

import scala.concurrent.*
import scala.concurrent.duration._
import scala.util.*
import scala.concurrent.ExecutionContext.Implicits.global

object PromiseCompose extends App {
  val promise1 = Promise[String]
  val promise2 = Promise[Int]

  promise1.future.onComplete {
    case Failure(exception) => throw exception
    case Success(value) => println(s"Promise1 == $value"); println("Completing promise2"); promise2 success 99999
  }

  promise2.future.onComplete {
    case Failure(exception) => throw exception
    case Success(value) => println(s"Promise2 == $value")
  }

  Future {
    Thread sleep 1000
    promise1 success "You've just completed the promise with me in it!"
  }

  Thread sleep 5100
}
