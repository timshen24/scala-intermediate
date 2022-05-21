import scala.concurrent.*
import scala.util.control.NonFatal

def runByPromise[T](block: => T)(implicit ec: ExecutionContext): Future[T] = {
  val p = Promise[T]
  ec.execute { () =>
    try {
      p.success(block)
    } catch {
      case NonFatal(e) => p.failure(e)
    }
  }
  p.future
}