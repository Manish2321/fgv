import org.apache.spark.sql.{Dataset, SparkSession}

object jdbctest extends  App{

  case class City(id: Int, name: String, population: Int, link: String)
  val spark =SparkSession.builder().master("local[*]").appName("db").getOrCreate()

  val jdbcDF = spark.read
    .format("jdbc")
    .option("url", "jdbc:mysql://localhost:3306/city_data")
    .option("dbtable", "city_data.City")
    .option("user", "root")
    .option("password", "Manish#2321#")
    .load()

  import spark.implicits._
  val cityDS: Dataset[City] = jdbcDF.as[City]

  println("Data in the form of table ")
  cityDS.show()

  println("Data in the form of List")
  val cityList: List[City] = cityDS.collect().toList
  cityList.foreach(println)

  // Write to a JSON file
  val outputFilePath = "city.txt"
  val tempDF = spark.createDataFrame(cityList) // Convert List to DataFrame
  tempDF.write.mode("overwrite").json("/Users/manishkumartyagi/Downloads/untitled/src/main/data/city.csv") // Write to JSON file


  // Read the JSON file back as a DataFrame



}
