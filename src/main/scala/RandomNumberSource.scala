import akka.stream.stage.{GraphStage, GraphStageLogic, OutHandler}
import akka.stream.{Attributes, Outlet, SourceShape}

import scala.util.Random

/**
  * Created by trozozti on 03/09/16.
  */
class RandomNumberSource extends GraphStage[SourceShape[Int ]] {

  private val out = Outlet.create[Int]("RandomNumberSource.out")

  override def createLogic(inheritedAttributes: Attributes): GraphStageLogic = new GraphStageLogic(shape) {
    private val random = new Random()

    setHandler(out, new OutHandler {
      override def onPull(): Unit = push(out, random.nextInt())
    })
  }

  override def shape = SourceShape.of(out)

}
