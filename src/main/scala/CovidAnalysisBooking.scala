//object CovidAnalysisBooking {
//  case class CovidSituation(city: String, country: String, timestamp: String, newCases: Int, reporter: String)
//
//  case class CovidResult1(city: String, country: String, date: String, totalCases: Int)
//
//  val covidList = List(
//    CovidSituation("Amsterdam", "Netherlands", "2020-01-01T00:00:00", 1200, "AMS01"),
//    CovidSituation("Amsterdam", "Netherlands", "2020-01-01T10:00:00", 1300, "AMS02"),
//    CovidSituation("Amsterdam", "Netherlands", "2020-01-01T12:00:00", 1400, "AMS01"),
//    CovidSituation("Utrecht", "Netherlands", "2020-01-01T00:00:00", 100, "UTR01")
//  )
//
//  val distinctSituation: CovidSituation = covidList.reduceByKey {
//    case (covid1: CovidSituation, covid2: CovidSituation) if covid1.reporter == covid2.reporter && covid1.timestamp >= covid2.timestamp => List(covid1)
//    case (covid1: CovidSituation, covid2: CovidSituation) if covid1.reporter == covid2.reporter && covid1.timestamp < covid2.timestamp => List(covid2)
//  }
//
//  def main(args: Array[String]): Unit = {
//    distinctSituation
//  }
//}
