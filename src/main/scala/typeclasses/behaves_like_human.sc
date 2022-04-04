// Step 0: existing data types:
sealed trait Animal
final case class Dog(name: String) extends Animal
final case class Cat(name: String) extends Animal
final case class Bird(name: String) extends Animal


// Step 1: define a behavior in a trait that takes a generic type
trait BehavesLikeHuman[A]:
  def speak(a: A): Unit
  def eatHumanFood(a: A): Unit


// Step 2: create instances for the types we care about (Dog, in this case)
object BehavesLikeHumanInstances:
  // implement an instance for a Dog
  given BehavesLikeHuman[Dog] = new BehavesLikeHuman[Dog]:
    def speak(dog: Dog): Unit = {
      println(s"I'm a Dog, my name is ${dog.name}")
    }
    def eatHumanFood(dog: Dog): Unit = {
      println(s"I ate the food you left on the table. It was good.")
    }

// Step 3a: add functions that can be used on a Dog instance; use like `speak(dog)`
object BehavesLikeHuman:
  def speak[A](a: A)(using behavesLikeHumanInstance: BehavesLikeHuman[A]): Unit =
    behavesLikeHumanInstance.speak(a)
  def eatHumanFood[A](a: A)(using behavesLikeHumanInstance: BehavesLikeHuman[A]): Unit =
    behavesLikeHumanInstance.eatHumanFood(a)

// Step 3b: add methods to dog class; use like `dog.speak`
object BehavesLikeHumanSyntax:
  extension[A] (value: A)
    def speak(using behavesLikeHumanInstance: BehavesLikeHuman[A]): Unit =
      behavesLikeHumanInstance.speak(value)
    def eatHumanFood(using behavesLikeHumanInstance: BehavesLikeHuman[A]): Unit =
      behavesLikeHumanInstance.eatHumanFood(value)

import BehavesLikeHumanInstances.given
import BehavesLikeHuman._

val rover = Dog("Rover")
BehavesLikeHuman.speak(rover)
BehavesLikeHuman.eatHumanFood(rover)

import BehavesLikeHumanSyntax._
rover.speak
rover.eatHumanFood
