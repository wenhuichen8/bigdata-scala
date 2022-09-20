package test

import scala.util.Random

/**
 * 7.2.	匹配类型
 * case y: Double if(y >= 0) => ...
模式匹配的时候还可以添加守卫条件。如不符合守卫条件，将掉入case _中
 */
object CaseDemo02 extends App{
  //val v = if(x >= 5) 1 else if(x < 2) 2.0 else "hello"
  val arr = Array("hello", 1, 2.0, CaseDemo02)
  val v = arr(Random.nextInt(4))
  println(v)
  v match {
    case x: Int => println("Int " + x)
    case y: Double if(y >= 0) => println("Double "+ y)
    case z: String => println("String " + z)
    case _ => throw new Exception("not match exception")
  }
}

