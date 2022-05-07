trait OutputChannel[-T]:
  def write(x: T): Unit

//val strOutputChannel: OutputChannel[String] = (x: String) => println(x)
val anyOutputChannel: OutputChannel[AnyRef] = (x: AnyRef) => println(x)
val strOutputChannel: OutputChannel[String] = anyOutputChannel

class Publication(val title: String)
class Book(title: String, author: String) extends Publication(title)

object Library:
  val books: Set[Book] = Set(Book("Programming in Scala", "Peter"), Book("Walden", "Jack"))
  def printBookList(info: Book => AnyRef) =
    for book <- books do println(info(book))

object Customer:
  def getTitle(p: Publication): String = p.title

//Library.printBookList(_.author) // author is inaccessible
import Customer._
val child: Publication => String = getTitle
val father: Book => AnyRef = child
