trait OutputChannel[-T]:
  def write(x: T): Unit

//val strOutputChannel: OutputChannel[String] = (x: String) => println(x)
val anyOutputChannel: OutputChannel[AnyRef] = (x: AnyRef) => println(x)
val strOutputChannel: OutputChannel[String] = anyOutputChannel