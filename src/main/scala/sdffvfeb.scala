//import ReadCitiesCSV.filePath
//import org.apache.spark
//import org.apache.spark.sql.{DataFrame, SparkSession}
//import org.apache.spark.sql.functions._
//
//import scala.io.StdIn
//
//val spark = SparkSession.builder()
//  .appName("India Export Analysis")
//  .getOrCreate()
//
//println("Enter the path to your export CSV file:")
//val filePath = StdIn.readLine()
//
//println("Enter the year (e.g., 2018):")
//val year = StdIn.readLine()
//
//println("Enter the country (e.g., 'INDIA'):")
//val country = StdIn.readLine()
//
//// Read data from the CSV file
//val df = spark.read.option("header", "true").csv(filePath)
//
//// Process the data directly
//val result = df.filter(col("year") === year && col("country") === country)
//  .groupBy("Commodity")
//  .agg(sum("value").cast("double").alias("total_value")) // Ensure the value is treated as double
//  .orderBy(desc("total_value"))
//  .limit(1) // Get the top commodity
//
//// Show the result
//result.show()
//
//// Stop the Spark session
//spark.stop()
