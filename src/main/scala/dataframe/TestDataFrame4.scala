package dataframe

import org.apache.spark.sql.{DataFrame, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 通过JSON文件创建DataFrames
 * {"name":"zhangsan","age":30}
 * {"name":"lisi","age":31}
 * {"name":"wangwu","age":32}
 */
object TestDataFrame4 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("TestDataFrame2").setMaster("local")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    val df: DataFrame = sqlContext.read.json("D:\\tmp\\people.json")
    df.createOrReplaceTempView("people")
    sqlContext.sql("select * from people").show()
  }
}
