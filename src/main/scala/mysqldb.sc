import org.apache.spark
import org.apache.spark.sql.SparkSession


val spark =SparkSession.builder().master("local[*]").appName("db").getOrCreate()

val jdbcDF = spark.read
  .format("jdbc")
  .option("url", "jdbc:mysql://localhost:3306/city_data")
  .option("dbtable", "city_data.city")
  .option("user", "username")
  .option("password", "Manish#2321#")
  .load()

jdbcDF.show()


val My_DF = spark.read.format("jdbc").option("driver","org.postgresql.Driver").
  option("url","jdbc:postgresql://localhost:5432/postgres")
  .option("user","manishtyagi")
  .option("password","root@123")
  .option("dbtable","public.City")
  .load()
My_DF.show()