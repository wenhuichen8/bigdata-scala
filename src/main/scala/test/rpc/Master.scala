package test.rpc

import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

class Master extends Actor {
  //用于接收消息
  override def receive: Receive = {
    case "connect" => {
      println("Master is connected")
      //返回消息
      sender() ! "reply"
    }
    case "hello" => {
      println("Master hello")
    }

  }

  //接收消息前执行
  override def preStart(): Unit = {
    println("Master is preStart")
  }
}

object Master {

  def main(args: Array[String]): Unit = {
    val configStr =
      """
        |akka.actor.provider = "akka.cluster.ClusterActorRefProvider"
        |akka.remote.artery.canonical.hostname = "127.0.0.1"
        |akka.remote.artery.canonical.port = "8888"
        |akka.cluster.seed-nodes =["akka://MyMasterSystem@127.0.0.1:8888"]
        |akka.downing-provider-class = "akka.cluster.sbr.SplitBrainResolverProvider"
        """.stripMargin;
    val config = ConfigFactory.parseString(configStr)
    //ActorSystem是老大，负责创建和监控下面的Actor；他是单列的
    val actorsystem = ActorSystem("MyMasterSystem", config)
    //通过ActorSystem创建Actor
    val myMaster = actorsystem.actorOf(Props(new Master), "MyMaster")
    //给myMaster发送消息
    myMaster ! "hello"
  }

}
