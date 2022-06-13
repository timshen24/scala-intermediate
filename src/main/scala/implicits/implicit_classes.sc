implicit class Ops1(list: List[Double]) {
  def +(other: List[Double]): List[Double] = {
    (list zip other).map(t => t._1 + t._2)
  }
}

List(1.0, 1.2) + List(3.2, 3.2)

case class Coordinate(latitude: Double, longitude: Double)

//object Coordinate {
  implicit class Ops2(latLong: (Double, Double)) {
    def toCoordinate: Coordinate = Coordinate(latLong._1,
      latLong._2)
  }
//}

import Coordinate._
(234.2, 4225.8).toCoordinate

extension (list: List[Double])
  def +(other: List[Double]): List[Double] =
    (list zip other).map(t => t._1 + t._2)

extension (latLong: (Double, Double))
  def toCoordinate: Coordinate =  Coordinate(latLong._1,
    latLong._2)