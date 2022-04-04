//implicit class Ops(list: List[Double]) {
//  def +(other: List[Double]): List[Double] = {
//    (list zip other).map(t => t._1 + t._2)
//  }
//}

List(1.0, 2.0) + List(3.0, 4.0)

implicit class Ops[T: Numeric](list: List[T]) {
  def +(other: List[T])(implicit ntc: Numeric[T]): List[T] = {
    val len = Math.max(list.length, other.length)
    val zero = ntc.zero
    (list.padTo(len,zero) zip other.padTo(len,zero)).map(t =>      ntc.plus(t._1, t._2))
  }
}

List(2.1, 3.4) + List(4.3, -2.3)
List(2.1f, 3.4f) + List(4.3f, -2.3f)
List(21, 34) + List(43, -23)
List(21L, 34L) + List(43L, -23L)
//List("one") + List("two")

extension[T: Numeric] (list: List[T])
  def +(other: List[T])(using n: Numeric[T]): List[T] =
    (list zip other).map(t => n.plus(t._1, t._2))
