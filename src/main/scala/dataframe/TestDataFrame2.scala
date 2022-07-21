package dataframe

import org.apache.spark.sql.SparkSession

object TestDataFrame2 {
  def main(args: Array[String]): Unit = {
    //1、创建sparksession
    val context = SparkSession.builder().appName("RDDToDataFrame").master("local").getOrCreate()
    //2、导入隐式转换和函数

    //3、读数据

  }
}
