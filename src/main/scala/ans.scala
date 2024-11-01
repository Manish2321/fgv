object Reverse {
  def main(args: Array[String]): Unit = {
    val reversePrinter = new ReversePrinter(args)
    println(reversePrinter.reverseArgs())
  }
}

class ReversePrinter(args: Array[String]) {
  def reverseArgs(): String = {
    args.reverse.mkString(" ")
  }
}
