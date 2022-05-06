import scala.collection.mutable.Queue

class Cell[T](init: T):
  private var current = init
  def get = current
  def set(x: T) = current = x

val c1 = new Cell[String]("abc")
val c2: Cell[Any] = c1
c2.set(1)
val s: String = c1.get

class Queue[+T] (private val leading: List[T], private val trailing: List[T]):
  def enqueue[U >: T](x: U): Queue[U] =
    new Queue[U](leading, x :: trailing)

class StrangeIntQueue extends Queue[Int]:
  override def enqueue(x: Int): StrangeIntQueue.this.type =
    println(math.sqrt(x))
    super.enqueue(x)

val x: Queue[Any] = new StrangeIntQueue
x.enqueue("abc")