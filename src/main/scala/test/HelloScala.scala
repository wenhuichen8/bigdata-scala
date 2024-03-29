package test

import scala.collection.mutable.ArrayBuffer

object HelloScala {
  def main(args: Array[String]): Unit = {
    println("hello scala,i love you!")
    val x = 0
    val result = {
      if (x < 0) {
        -1
      } else if (x >= 1) {
        1
      } else {
        "error"
      }
    }
    println(result)

    for (i <- 1 to 10) {
      println(i)
    }
    println("=======================")
    val arr = Array("a", "b", "c")
    for (i <- arr)
      println(i)
    println("=======================")
    for (i <- 1 to 3; j <- 1 to 3 if i != j)
      println((10 * i + j) + " ")

    val a1 = Array(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val a2 = for (i <- a1; if (i % 2 == 0)) yield i
    val a3 = a1.filter(_ % 2 == 0)
    println("=======================")
    for (i <- a1.indices) {
      println(a1(i))
    }
    println("===============xx========")
    println(m3(fun1))
    println("===============xx========")
    val ab = new ArrayBuffer[Int]()

    ab += 1;
    ab += (1, 2, 3, 4)
    ab ++= Array(5, 6, 8, 9)
    println(ab)
    println("===============xx========")
    ab.insert(0, 100)
    println(ab)
    println("===============xx========")
    ab.remove(0, 2)
    println(ab)
  }

  def m1(a: Int, b: Int): Int = {
    a * b
  }

  def m2(a: Int): Int = {
    a * 100
  }
  //函数名  =函数参数 =>函数体
  val fun1 = (i: Int) => i * 10;
  val r = 1 to 10;
  r.map(fun1)
  r.map(m2)

  //方法参数是函数
  def m3(func: Int => Int): Int = {
    //调用函数
    func(3)
  }

  val fun2 = (i: Int, j: Int) => (i + j) * 10;

  //方法参数是 函数
  def m4(func: (Int, Int) => Int): Int = {
    //调用函数
    func(3, 4)
  }
  //函数func3 参数Int => 返回类型 = 函数体，其中x为传进来的参数
  var func3: Int => String = { x => x.toString; }
  //func3 与 func4 等价
  var func4 = (x: Int) => {
    x.toString;
  }
  //函数 func5 参数x,y => 返回 (y, x),返回的为元组
  val func5 = (x: Int, y: Double) => (y, x)

  //func5 与 func6 等价，a和 b为传进来的参数
  val func6: (Int, Double) => (Double, Int) = {
    //a和 b为传进来的参数 => 使用参数的代码
    (a, b) => (b, a)
  }

  

}
