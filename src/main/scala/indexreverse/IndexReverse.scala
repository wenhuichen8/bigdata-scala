package indexreverse

import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.hadoop.conf.Configuration

object IndexReverse {
  def main(args: Array[String]): Unit = {
    //System.setProperty("hadoop.home.dir", "D:\\setup\\hadoop-3.2.3")
    //val inputUrl="D:\\data\\index.txt"

    val inputUrl="hdfs://localhost:9000/chenwenhui/index"
    val outPath="/chenwenhui/out/index"

    val sparkConf = new SparkConf().setAppName("IndexReverse").setMaster("local[1]")
    val sc = new SparkContext(sparkConf)
    //调用Spark读取文件API,读取文件内容
    val wordRDD = sc.textFile(inputUrl)
      .flatMap { //使用flatMap进行分词后展开
        line =>
          val array = line.split("\\.", 2) //以.做分词需要加转义符
          val fileName = array(0)
          array(1).split("\"")(1).split(" ").map(word => (fileName, word))
      }
    //print("ddddd:"+wordRDD.collect.toBuffer)
    //wordRDD:((0,it), (0,is), (0,what), (0,it), (0,is), (1,what), (1,is), (1,it), (2,it), (2,is), (2,a), (2,banana))
    val indexResult = wordRDD.map(kv => (kv._2, kv._1)).map((_, 1))
      .reduceByKey((x, y) => x + y)
      .map { case ((k, v), cnt) => (k, (v, cnt)) }
      .groupByKey() //只分组不聚合
    val config = new Configuration
    val fs = FileSystem.get(config)
      if (fs.exists(new Path(outPath))) fs.delete(new Path(outPath), true)
      indexResult.saveAsTextFile("hdfs://localhost:9000" + outPath)

    print("结果:" + indexResult.collect().toBuffer)
  }
}
