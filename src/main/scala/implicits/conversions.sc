import scala.language.implicitConversions

case class Fahrenheit(degrees: Double)
case class Celsius(degrees: Double)

object Fahrenheit {
  implicit def f2c(f: Fahrenheit): Celsius =
    Celsius((f.degrees - 32.0) * (5.0 / 9.0))
}

object Celsius {
  implicit def c2f(c: Celsius): Fahrenheit =
    Fahrenheit((c.degrees * 9.0 / 5.0) + 32.0)
}

def heatItUp(c: Celsius) = Celsius(c.degrees + 10.0)

heatItUp(Fahrenheit(72.0))

//val warmerRoom: Fahrenheit = res0

import Celsius._
c2f(heatItUp(Fahrenheit(72.0)))

object MyLibrary:
  given Conversion[Fahrenheit, Celsius] = f =>
    Celsius((f.degrees - 32.0) * (5.0 / 9.0))