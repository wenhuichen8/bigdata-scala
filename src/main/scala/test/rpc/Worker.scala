package test.rpc

import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

class Worker extends Actor {

  override def preStart(): Unit = {
    println("preStart is Starting ")
    //通过actorSelection在查找通信的Actor
    val MyMaster = context.system.actorSelection("akka://MyMasterSystem@127.0.0.1:8888/user/MyMaster")
    //Worker向Master发送消息
    MyMaster ! "connect"
  }

  override def receive: Receive = {
    case "reply" => {
      println("reply from Master!")
    }
    case "hello" => {
      println("Worker is Hello")
    }
  }
}
object Worker{

  def main(args: Array[String]): Unit = {
    //定义服务端的ip和端口
    val host = "127.0.0.1"
    val port = 8088
    /**
     * 使用ConfigFactory的parseString方法解析字符串,指定服务端IP和端口
     */
    val config = ConfigFactory.parseString(
      s"""
         |akka.actor.provider = "akka.cluster.ClusterActorRefProvider"
         |akka.remote.artery.canonical.hostname = $host
         |akka.remote.artery.canonical.port = $port
         """.stripMargin)
    //ActorSystem是老大，负责创建和监控下面的Actor；他是单列的
    val actorsystem = ActorSystem("MyWorkerSystem", config)
    //通过ActorSystem创建Actor
    val MyWorker = actorsystem.actorOf(Props(new Worker), "MyWorker")
    //给myMaster发送消息
    MyWorker ! "hello"

  }
}
