trait SequentialChecker[F[_]] {
  def isSequential: Boolean
}

val listChecker = new SequentialChecker[List] {
  override def isSequential: Boolean = true
}