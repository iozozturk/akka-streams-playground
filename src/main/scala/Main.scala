import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{RunnableGraph, Sink, Source}

/**
  * Created by trozozti on 03/09/16.
  */
object Main extends App {

  final val system = ActorSystem.create("StreamsExamples")
  implicit val mat = ActorMaterializer.create(system)

  val numberSource = Source.fromGraph(new RandomNumberSource).mapMaterializedValue(o => NotUsed.getInstance())

  private val runnableGraph: RunnableGraph[NotUsed] = numberSource.take(10).to(Sink.foreach(println))

  Source.single("happy hakking").to(new PrintlnSink("ismet")).run
  Source(List("hello","world")).to(new PrintlnSink("ismet")).run

  runnableGraph.run
  runnableGraph.run
  runnableGraph.run
}
