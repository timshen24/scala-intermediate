import java.text.NumberFormat

trait Converter[T] {
  def parse(s: String): T
}

class NumberConverter(f: NumberFormat) extends Converter[Number] {
  override def parse(s: String): Number = f.parse(s)
}

case class Color(red: Double, green: Double, blue: Double)

object ColorConverter extends Converter[Color] {
  override def parse(s: String): Color = s match {
    case "Red" => Color(1.0, 0.0, 0.0)
    case "Green" => Color(0.0, 1.0, 0.0)
    case "Blue" => Color(0.0, 0.0, 1.0)
    case _ => Color(0.0, 0.0, 0.0)
  }
}

object Converter {
  implicit class StringOps(s: String) {
    def parse[T](implicit c: Converter[T]): T = c.parse(s)
  }
}

implicit val color: Converter[Color] = ColorConverter
implicit val percent: NumberConverter = new
    NumberConverter(NumberFormat.getPercentInstance())

import Converter._
"Red".parse[Color]
"130%".parse[Number]
