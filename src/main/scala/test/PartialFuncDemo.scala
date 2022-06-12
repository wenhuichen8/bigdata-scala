package test

/**
 * Created by IntelliJ IDEA.
 * User: chenwenhui
 * Date: 2022/6/4
 * Time: 18:29
 * To change this template use File | Settings | File Templates.
 */
object PartialFuncDemo {

  def func1: PartialFunction[String, Int] = {
    case "one" => 1
    case "two" => 2
    case _ => -1
  }

  def func2(num: String): Int = num match {
    case "one" => 1
    case "two" => 2
    case _ => -1
  }

  def main(args: Array[String]) {
    println(func1("one"))
    println(func2("one"))
  }

}
