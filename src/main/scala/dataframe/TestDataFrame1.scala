package dataframe

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 通过case class创建DataFrames（反射）
 * people.txt
 * wenhui,100
 * johh,99
 */
object TestDataFrame1 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("RDDToDataFrame").setMaster("local")
    val sc = new SparkContext(conf)
    val context = new SQLContext(sc)
    // 将本地的数据读入RDD，并将RDD 与case class 关联
    val peopleRDD = sc.textFile("D:\\tmp\\people.txt").map(line => People(line.split(",")(0), line.split(",")(1).trim.toInt))
    import context.implicits._
    // 将RDD 转换成DataFrames
    val df = peopleRDD.toDF
    //将DataFrames创建成一个临时的视图
    df.createOrReplaceTempView("people")
    //使用SQL语句进行查询
    context.sql("select * from people").show()
  }
}
