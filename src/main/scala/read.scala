import org.apache.spark.sql.{DataFrame, SparkSession}
import java.nio.file.{Files, Paths}

object ReadCitiesCSV extends App {

  val spark = SparkSession.builder()
    .master("local[*]")
    .appName("Read City Data")
    .getOrCreate()

  println("Read And Print Saved Data")

  // Path to the CSV file
  val filePath = "/Users/manishkumartyagi/Downloads/untitled/src/main/scala/city.csv"

  // Check if the CSV file exists before reading
  if (Files.exists(Paths.get(filePath))) {
    // Read the CSV file
    val readDF: DataFrame = spark.read
      .option("header", "true") // Use the first line as header
      .option("inferSchema", "true") // Automatically infer data types
      .csv(filePath)

    // Show the DataFrame
    readDF.show()

    // Sort by id
    val sortedDF = readDF.sort("id")

    // Show the sorted DataFrame
    sortedDF.show()
  } else {
    println(s"CSV file does not exist at path: $filePath")
  }

}
