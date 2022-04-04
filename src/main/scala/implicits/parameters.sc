import java.text.NumberFormat

//def makeString(n: Double, f: NumberFormat): String = f.format(n)
//
//def reportError(n: Double, f: NumberFormat): String =
//  makeString(n,f) + " isn't allowed here."
//
//def printDouble(n: Double, f: NumberFormat): String =
//  makeString(n * 2,f)

def makeString(n: Double)(implicit f: NumberFormat): String = f.format(n)

implicit val formatter: NumberFormat = NumberFormat.getPercentInstance()

makeString(2.34)

def implicitlyMakeString(n: Double): String =
  implicitly[NumberFormat].format(n)

implicitlyMakeString(0.6)

def printDouble(n: Double)(using f: NumberFormat): String =
  makeString(n * 2)

//given fmt: NumberFormat = NumberFormat.getPercentInstance
printDouble(2.32424)

def reportError(n: Double): String =
  summon[NumberFormat].format(n) + " isn't allowed here."

reportError(0.314)