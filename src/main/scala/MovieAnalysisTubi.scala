import scala.io.Source

object MovieAnalysisTubi {
  def autoClose(a: => Source): Unit = {
    var source: Source = ???
    try source = a
    catch {
      case e: Exception => throw e
    } finally source.close()
  }

  def main(args: Array[String]): Unit = {
    val source = Source
      .fromURL(
        "https://gist.githubusercontent.com/CatTail/18695526bd1adcc21219335f23ea5bea/raw/54045ceeae6a508dec86330c072c43be559c233b/movies.csv"
      )
    val movies: List[String] = source.getLines().toList
    println(s"movies.length = ${movies.length}")
    val ratings: List[(String, Double)] = movies.map { line =>
      (line, line.split(",").last.toDoubleOption.getOrElse(0))
    }
    ratings
      .sortBy { case (line: String, rating: Double) =>
        (-1 * rating, line)
      }
      .take(10)
      .foreach(println)
    source.close()
  }
}
