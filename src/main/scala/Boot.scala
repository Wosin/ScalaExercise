import model._
import solving.SolutionFinder

import scala.annotation.tailrec

object Boot extends App {
  override def main(args: Array[String]): Unit = {
    val t0 = System.nanoTime();
    val result = SolutionFinder.findSolutionForGivenBoardSize(Seq(Rook, Rook, Knight, Knight, Knight, Knight).sortBy(_.sortingPriority), 4,4)
    val t1 = System.nanoTime();
    println("TIME TOOK:" + (t1-t0)/1000000000.0)
    println("RESULTS", result.size)
    result.take(10).foreach(println)
  }

}
