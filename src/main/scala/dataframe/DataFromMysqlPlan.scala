package dataframe

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

import java.util.Properties

//用sparksql加载mysql表中的数据,
/**
 * 运行时可能报内存不够错误：需加参数
 * -Xms256m -Xmx256m -Xmn125m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=320m
 * -Xms256m -Xmx512m -XX:PermSize=128M -XX:MaxPermSize=256M
 * */
object DataFromMysqlPlan {

  def main(args: Array[String]): Unit = {
    //1、创建SparkConf对象
    val sparkConf: SparkConf = new SparkConf().setAppName("DataFromMysql").setMaster("local")
    //sparkConf.set("spark.sql.codegen.wholeStage","true")
    //2、创建SparkSession对象
    val spark: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()
    spark.sparkContext.setLogLevel("WARN")

    //3、读取mysql表的数据
    // 3.1 指定mysql连接地址
    val url = "jdbc:mysql://localhost:3306/mydb?characterEncoding=UTF-8"
    //3.2 指定要加载的表名
    val student = "students"
    val score = "scores"
    // 3.3 配置连接数据库的相关属性
    val properties = new Properties()
    //用户名
    properties.setProperty("user", "root")
    //密码
    properties.setProperty("password", "root")
    val studentFrame: DataFrame = spark.read.jdbc(url, student, properties)
    val scoreFrame: DataFrame = spark.read.jdbc(url, score, properties)
    //把dataFrame注册成表
    studentFrame.createTempView("students2")
    scoreFrame.createOrReplaceTempView("scores")
    val resultFrame: DataFrame = spark.sql("SELECT temp1.class,SUM(temp1.degree),AVG(temp1.degree)  " +
      "FROM (SELECT  students.sno AS ssno,students.sname,students.ssex,students.sbirthday,students.class, scores.sno,scores.degree,scores.cno  " +
      "FROM students2 as students LEFT JOIN scores ON students.sno =  scores.sno  " +
      "WHERE degree > 60 AND sbirthday > '1973-01-01 00:00:00' ) " +
      "temp1 GROUP BY temp1.class")
    resultFrame.explain(true)
    resultFrame.show()
    spark.stop()
  }
}