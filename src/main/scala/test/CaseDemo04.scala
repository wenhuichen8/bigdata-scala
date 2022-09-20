package test

import scala.util.Random

/**
 * 7.4.	样例类
 */
object CaseDemo04 extends App {

  case class SubmitTask(id: String, name: String)

  case class HeartBeat(time: Long)

  case object CheckTimeOutTask

  val arr = Array(CheckTimeOutTask, HeartBeat(1231), SubmitTask("0001", "task-0001"))

  arr(1) match {
    case SubmitTask(id, name) => {
      println(s"$id, $name") //前面需要加上s, $id直接取id的值
    }
    case HeartBeat(time) => {
      println(time)
    }
    case CheckTimeOutTask => {
      println("check")
    }
  }


}
