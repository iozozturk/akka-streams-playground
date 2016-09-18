import akka.stream.{Attributes, Inlet, SinkShape}
import akka.stream.stage.{GraphStage, GraphStageLogic, InHandler}

/**
  * Created by trozozti on 03/09/16.
  */
class PrintlnSink(val prefix: String) extends GraphStage[SinkShape[String]] {
  private val inlet = Inlet.create[String]("PrintlnSink.in")

  override def createLogic(inheritedAttributes: Attributes): GraphStageLogic = new GraphStageLogic(shape) with InHandler{
    var count = 0l

    override def onPush(): Unit = {
      val elem = grab(inlet)
      count += 1

      println(String.format(s"[$prefix:$count] $elem"))
      pull(inlet)
    }

    setHandler(inlet, this)

    override def preStart() = pull(inlet)
  }

  override def shape: SinkShape[String] = SinkShape.of(inlet)
}
