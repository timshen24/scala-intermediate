package fp_exercises

object HelloWorldNTimes extends App {
  def f(i: Int) =
    (1 to i).foreach(_ => println("Hello World"))

  var n = scala.io.StdIn.readInt
  f(n)
}

def f1(num:Int,arr:List[Int]):List[Int] =
  arr.flatMap(i => List.fill[Int](num)(i))

def f2(delim:Int,arr:List[Int]):List[Int] =
  arr.filter(_ < delim)

def f3(arr:List[Int]):List[Int] =
  arr.zipWithIndex.collect{case (e, id) if id % 2 == 1 => e} // Can only do this way

def f4(num:Int) : List[Int] = {
  import scala.io.StdIn._
  val list = (0 until num).toList
  //println(s"[${list.mkString(", ")}]")
  list
}

def f5(arr:List[Int]):Int = arr.foldLeft(0)((acc, _) => acc+1)


