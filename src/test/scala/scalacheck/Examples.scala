package scalacheck

import org.scalacheck.Gen
import org.scalatest.funsuite.AnyFunSuite
import org.scalacheck.Prop.forAll

class Examples extends AnyFunSuite {
  test("Invoking head on an empty Set should produce NoSuchElementException") {
    assertThrows[NoSuchElementException] {
      Set.empty.head
    }
  }

  test("List sizes be equal before and after concatenation") {
    forAll { (l1: List[Int], l2: List[Int]) =>
      l1.size + l2.size == (l1 ::: l2).size }
  }

  def httpTypeGen: Gen[String] = Gen.oneOf(Seq("http", "https"))
  def domainGen: Gen[String] = Gen.nonEmptyListOf(Gen.alphaNumStr).map(_.mkString("."))
  def domainTypeGen: Gen[String] = Gen.oneOf(Seq("com", "es", "org"))
  def pathGen: Gen[String] = Gen.nonEmptyListOf(Gen.alphaNumStr).map(_.mkString("/"))

  def urlGen: Gen[String] =
    for {
      http       <- httpTypeGen
      domain     <- domainGen
      domainType <- domainTypeGen
      path       <- pathGen
    } yield http + "//" + domain + "." + domainType + "/" + path

  def twoNumberGen: Gen[(Int, Int)] =
    for {
      first <- Gen.posNum[Int]
      second <- Gen.posNum[Int]
    } yield if (first > second) (first, second) else (second, first)

  case class Person(name: String, surname: String)

  def personGenerator: Gen[Person] =
    for {
      randomName    <- Gen.alphaNumStr
      randomSurname <- Gen.alphaNumStr
    } yield Person(randomName, randomSurname)

  def personGenerator(name: Option[String]): Gen[Person] =
    for {
      randomName    <- name.fold(Gen.alphaNumStr)(inputName => Gen.const(inputName))
      randomSurname <- Gen.alphaNumStr
    } yield Person(randomName, randomSurname)

  case class Account(accNumber: String, balance: Int)

  def accountNumberGen: Gen[String] = Gen.listOfN(22, Gen.chooseNum(0,9)).map(_.toString)
  def AccountGenerator(balanceGen: Gen[Int] = Gen.const(0)): Gen[Account] = for {
    accNumber <- accountNumberGen
    balance <- balanceGen
  } yield Account(accNumber, balance)

  test("urlGen should have positive length") {
    forAll(urlGen) {
      url => url.nonEmpty
    }
  }

}

