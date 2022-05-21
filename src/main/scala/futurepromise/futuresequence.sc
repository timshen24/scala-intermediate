import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.*
import scala.concurrent.duration._
import scala.util.*

def donutStock(donut: String): Future[Option[Int]] = Future {
  // assume some long running database operation
  println("checking donut stock")
  Some(10)
}

println(s"\nStep 1: Create a List of future operations")
val futureOperations = List(
  donutStock("vanilla donut"),
  donutStock("plain donut"),
  donutStock("chocolate donut")
)

println(s"\nStep 2: Call Future.sequence to run the future operations in parallel")
val futureSequenceResults = Future.sequence(futureOperations)
futureSequenceResults.onComplete {
  case Success(results) => println(s"Results $results")
  case Failure(e)       => println(s"Error processing future operations, error = ${e.getMessage}")
}

println(s"\nStep 3: Call Future.foldLeft to fold over futures results from left to right")
val futureFoldLeft = Future.foldLeft(futureOperations)(0){ case (acc, someQty) =>
  acc + someQty.getOrElse(0)
}

futureFoldLeft.onComplete {
  case Success(results) => println(s"Results $results")
  case Failure(e)       => println(s"Error processing future operations, error = ${e.getMessage}")
}