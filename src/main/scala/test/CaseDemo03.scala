package test

/**
 * 7.3.	匹配数组
 */
object CaseDemo03 extends App {
  var arr = Array(1, 3, 5)
  arr = Array(0)
  arr = Array(0, 3, 5)
  arr match {
    case Array(1, x, y) => println(x + " " + y)
    case Array(0) => println("only 0")
    case Array(0, _*) => println("0 ...")
    case _ => println("something else")
  }
  println("=========================================")

  var lst = List(0, 3, 111)
  lst = List(0, 3)
  lst = List(0, 3, 4, 5)
  lst match {
    case 0 :: Nil => println("only 0")
    case x :: y :: Nil => println(s"x:$x y:$y")
    case 0 :: a => println(s"0....$a")
    case _ => println("something else")
  }
  println("==========7.3.\t匹配元组===============================")
  val tup = (2, 3, 7)
  tup match {
    case (1, x, y) => println(s"1, $x , $y")
    case (_, z, 5) => println(z)
    case  _ => println("else")
  }

}
