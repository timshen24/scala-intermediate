package multithread

import java.util.concurrent.Executors
import scala.concurrent.{ExecutionContext, Future}

object ThreadLimitations extends App {
  // Daniel's rants
  // DR #1: OO encapsulation is only valid in the SINGLE-THREADED MODEL

  class BankAccount(private var amount: Int) {
    override def toString = s"$amount"

    def withdraw(money: Int): Unit = synchronized {
      this.amount -= money
    }

    def deposit(money: Int): Unit = synchronized {
      this.amount += money
    }

    def getAmount: Int = amount
  }

  val account = new BankAccount(2000)
  for (_ <- 1 to 2000) {
    new Thread(() => account.withdraw(1)).start()
  }

  for (_ <- 1 to 2000) {
    new Thread(() => account.deposit(1)).start()
  }

  println(account.getAmount)

  // OOP encapsulation is broken in a multithreaded env
  // synchronization! Locks to the rescue
  // But lead to deadLocks, liveLocks, ...

  // DR #2 - delegating a task to a thread is a PAIN
  // you have a running thread and you want to pass a runnable to that thread
  var task: Runnable = null

  val runningThread: Thread = new Thread(() => {
    while (true) {
      while (task == null) {
        runningThread.synchronized {
          println("[background] waiting for a task")
          runningThread.wait()
        }
      }

      task.synchronized {
        println("[background] I have a task!")
        task.run()
        task = null
      }
    }
  })

  def delegateToBackgroundThread(r: Runnable) = {
    if (task == null) {
      task = r
      runningThread.synchronized {
        runningThread.notify()
      }
    }
  }

  def demoBackgroundDelegation(): Unit = {
    runningThread.start()
    Thread.sleep(1000)
    delegateToBackgroundThread(() => println("I'm running from another thread"))
    Thread.sleep(1000)
    delegateToBackgroundThread(() => println("This should run in the background again"))
  }

//  demoBackgroundDelegation()

  // DR #3: tracing and dealing with errors is a PITN in multithreaded/distributed apps

  implicit val ec: ExecutionContext = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(8))
  // sum 1M numbers in between 10 threads

  val futures = (0 to 9)
    .map(i => BigInt(100000 * i) until BigInt(100000 * (i + 1))) // 0 - 99999, 100000 - 199999, and so on
    .map(range => Future {
      // bug
      if (range.contains(BigInt(546732))) throw new RuntimeException("invalid number")
      range.sum
    })

  val sumFuture = Future.reduceLeft(futures)(_ + _)
  sumFuture.onComplete(println)
}
