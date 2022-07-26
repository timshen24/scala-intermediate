val checkerBoard = List(1, 2, 3).flatMap(n => List("a", "b", "c").map(c => (n, c)))
val anotherCheckerBoard = for {
  n <- List(1, 2, 3)
  c <- List("a", "b", "c")
} yield (n, c)

for {
  c <- 'a' to 'z'
  i <- 1 to 10 if i % 2 == 0
} yield (c, i)