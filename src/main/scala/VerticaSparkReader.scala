package com.example

import java.util.Properties
import org.apache.spark.sql.SparkSession

object VerticaReader {

  def main(args: Array[String]): Unit = {

    // Create a SparkSession
    val spark = SparkSession.builder()
      .appName("VerticaSparkReader")
      .master("local[*]")
      .getOrCreate()

    // JDBC connection properties
    val connectionProperties = new Properties()
    connectionProperties.put("user", "your-username")
    connectionProperties.put("password", "your-password")
    connectionProperties.put("driver", "com.vertica.jdbc.Driver")

    // Read from Vertica using Spark JDBC
    val jdbcDF = spark.read
      .jdbc("jdbc:vertica://your-hostname:5433/your-database", "your-table", connectionProperties)

    // Print the schema of the DataFrame
    jdbcDF.printSchema()

    // Show the data
    jdbcDF.show()

    // Stop the SparkSession
    spark.stop()
  }
}
