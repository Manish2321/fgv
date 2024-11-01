import java.io.{File, PrintWriter}
import java.sql.{Connection, DriverManager, ResultSet, SQLException}

case class City(id: Int, name: String, population: Int, link: String)

object DbConnect extends App {

  val jdbcUrl = "jdbc:mysql://localhost:3306/city_data"
  val uname = "root"
  val pwd = "Manish#2321#"
  var connection: Connection = null
  var cities: List[City] = List()

  try {
    Class.forName("com.mysql.cj.jdbc.Driver")
    connection = DriverManager.getConnection(jdbcUrl, uname, pwd)
    val statement = connection.createStatement()
    val resultSet: ResultSet = statement.executeQuery("SELECT * FROM city")

    while (resultSet.next()) {
      val city = City(
        id = resultSet.getInt("id"),
        name = resultSet.getString("name"),
        population = resultSet.getInt("population"),
        link = resultSet.getString("link")
      )
      cities = cities :+ city
    }
    cities.foreach(println)

  } catch {
    case e: ClassNotFoundException =>
      println("Error loading JDBC driver class!!")
      e.printStackTrace()
    case e: SQLException =>
      println("Error connecting to the database!!")
      e.printStackTrace()
  } finally {
    if (connection != null) {
      try {
        connection.close()
      } catch {
        case e: SQLException =>
          println("Error closing the connection!")
          e.printStackTrace()
      }
    }
  }

  // Write cities to the specified file path
  writeCitiesToFile(cities, "/Users/manishkumartyagi/Downloads/untitled/src/main/scala/city.csv")

  def writeCitiesToFile(cities: List[City], fileName: String): Unit = {
    val file = new File(fileName)
    val writer = new PrintWriter(file)

    try {
      cities.foreach { city =>
        writer.write(s"${city.id}, ${city.name}, ${city.population}, ${city.link}\n")
      }
    } catch {
      case e: Exception =>
        println("Error writing to file!")
        e.printStackTrace()
    } finally {
      writer.close()
    }
  }
}



