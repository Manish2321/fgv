import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.logging.log4j.{LogManager, Logger}

object SparkArrayExample {
  val logger: Logger = LogManager.getLogger(this.getClass)
  def main(args: Array[String]): Unit = {
    // Create a Spark session
    val spark = SparkSession.builder()
      .appName("Spark Array Example")
      .master("local[*]") // Use local mode for testing
      .getOrCreate()

    import spark.implicits._

    // Create a DataFrame with an array column
    val df = Seq(
      (1, Array(1, 2, 3)),
      (2, Array(4, 5, 6)),
      (3, Array(7, 8, 9))
    ).toDF("id", "numbers")

    // Show the original DataFrame
    df.show()

    // Explode the array into separate rows
    val explodedDF = df.withColumn("number", explode($"numbers"))
    println("Exploded DataFrame:")
    explodedDF.show()

    // Use a UDF to square each number in the array
    val squareUDF = udf((arr: Seq[Int]) => arr.map(x => x * x))
    val squaredDF = df.withColumn("squared_numbers", squareUDF($"numbers"))
    println("DataFrame with Squared Numbers:")
    squaredDF.show()

    // Stop the Spark session
    spark.stop()
  }
}
