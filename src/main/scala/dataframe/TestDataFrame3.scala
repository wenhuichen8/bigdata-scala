package dataframe

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Row, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 通过structType创建DataFrames（编程接口）
 * people.txt
 * wenhui,100
 * johh,99
 */
object TestDataFrame3 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("TestDataFrame3").setMaster("local")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    val fileRDD = sc.textFile("D:\\tmp\\people.txt")
    // 将RDD 数据映射成Row，需要import org.apache.spark.sql.Row
    val rowRDD: RDD[Row] = fileRDD.map(line => {
      val fields = line.split(",")
      Row(fields(0), fields(1).trim.toInt)
    })
    // 创建StructType来定义结构
    val structType: StructType = StructType(
      //字段名，字段类型，是否可以为空
      StructField("name", StringType, true) :: StructField("age", IntegerType, true) :: Nil)
    /** * rows: java.util.List[Row], * schema: StructType * */
    val df: DataFrame = sqlContext.createDataFrame(rowRDD, structType)
    df.createOrReplaceTempView("people")
    sqlContext.sql("select * from people").show()
  }
}
