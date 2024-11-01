import scala.io.Source
import scala.collection.mutable
import scala.collection.immutable.SortedMap

object WordCount extends App {

  def countWords(filename: String): Unit = {

    val source = Source.fromFile(filename)
    val words = source.getLines().flatMap(_.split("\\W+")).toList
    source.close()

    // Using a mutable map
    val mutableMap = mutable.Map[String, Int]().withDefaultValue(0)
    for (word <- words) {
      mutableMap(word) += 1
    }

    println("Top 5 Mutable Map Results:")
    mutableMap.toList.sortBy(-_._2).take(5).foreach { case (word, count) => println(s"$word: $count") }

    // Using an immutable map
    val immutableMap = words.foldLeft(Map[String, Int]().withDefaultValue(0)) {
      (map, word) => map + (word -> (map(word) + 1))
    }

    println("\nTop 5 Immutable Map Results:")
    immutableMap.toList.sortBy(-_._2).take(5).foreach { case (word, count) => println(s"$word: $count") }

    // Using a sorted map
    val sortedMap = words.foldLeft(SortedMap[String, Int]().withDefaultValue(0)) {
      (map, word) => map + (word -> (map(word) + 1))
    }

    println("\nTop 5 Sorted Map Results:")
    sortedMap.toList.sortBy(-_._2).take(5).foreach { case (word, count) => println(s"$word: $count") }
  }

  val words = "/Users/manishkumartyagi/Downloads/untitled/src/main/data/wordCount.txt"
  countWords(words)
}
