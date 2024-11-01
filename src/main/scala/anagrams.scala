import scala.io.Source
import scala.collection.mutable

object AnagramGrouper extends App {


  def groupAnagrams(filename: String): Unit = {

    val source = Source.fromFile(filename)
    val words = source.getLines().flatMap(_.split("\\W+")).toList
    source.close()

    // Group anagrams
    val anagramGroups = mutable.Map[String, mutable.ListBuffer[String]]()

    for (word <- words) {
      // Normalize the word: convert to lowercase and sort the characters
      val normalized = word.toLowerCase.sorted
      // Add the word to the appropriate group
      if (!anagramGroups.contains(normalized)) {
        anagramGroups(normalized) = mutable.ListBuffer[String]()
      }
      anagramGroups(normalized) += word
    }

    //  anagram groups by size
    val topAnagramGroups = anagramGroups.toList
      .sortBy(-_._2.size) // Sort by the size of the groups in descending order

    topAnagramGroups.foreach { case (normalized, words) =>
      println(s" ${words.mkString(", ")} | Count: ${words.size}")
    }
  }

  val filename = "/Users/manishkumartyagi/Downloads/untitled/src/main/data/wordCount.txt"
  groupAnagrams(filename)
}
