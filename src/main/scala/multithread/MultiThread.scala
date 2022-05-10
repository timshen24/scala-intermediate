package multithread

import scala.concurrent.Future
import scala.util._

object MultiThread extends App {
  val aThread = new Thread(() => println("I'm running in parallel"))
  aThread.start()
  aThread.join()

  val threadHello = new Thread(() => (1 to 1000).foreach(_ => println("hello")))
  val threadGoodBye = new Thread(() => (1 to 1000).foreach(_ => println("goodbye")))

  threadHello.start()
  threadGoodBye.start()

  // different runs produce different results
  class BankAccount(/*@volatile */private var amount: Int) {
    override def toString: String = "" + amount

    def withdraw(money: Int): Unit = this.amount -= money

    def safeWithdraw(money: Int): Unit = this.synchronized {
      this.amount -= money
    }
  }

  /**
   * BA(1000)
   * T1 -> withdraw 1000
   * T2 -> withdraw 2000
   *
   * T1 -> this.amount = this.amount - ... // PREEMPTED by the OS
   * T2 -> this.amount = this.amount - 2000 = 8000
   * T1 -> -1000 = 9000
   *
   * => result = 9000
   *
   * this.amount -= this.amount
   */

  // inter-thread communication on the JVM
  // wait-notify mechanism

  import scala.concurrent.ExecutionContext.Implicits.global
  val future = Future {
    42
  }
  future.onComplete {
    case Success(_) => println("I found the meaning of life")
    case Failure(exception) => throw exception
  }

  val aProcessFuture = future.map(_ + 1)
  val aFlatFuture = future.flatMap(value => Future(value + 2))
  val filteredFuture = future.filter(_ % 2 == 0)

  // for comprehensions
  val aNonsenseFuture = for {
    meaningOfLife <- future
    filteredMeaning <- filteredFuture
  } yield meaningOfLife + filteredMeaning

  // andThen, recover/recoverWith

  // Promises
}
