import casting.TypeCasting
import solving.SolutionFinder

import TypeCasting._
object Boot extends App {
  override def main(args: Array[String]): Unit = {
    println("Hello! This application will calculate all possible positions for ChessPieces on ChessBoard ")
    println("Please give the width size of the board(default width is 7) :")
    val width = safeStringToInt(scala.io.StdIn.readLine())
    println("Please give the height size of the board(default height is 7) :")
    val height = safeStringToInt(scala.io.StdIn.readLine())
    println("Please give the space separated letters for Chess pieces : [K]ing, [Q]ueen, [R]ook, K[N]ight, [B}ishop.")
    val pieces = safeStringToPieces(scala.io.StdIn.readLine())
    println("Calculating...")

    val result = measureSolutionFindingTime(SolutionFinder.findSolutionForGivenBoardSize(pieces, width, height))

    println("I have found " + result.size + " solutions.")
    println("To print all solution input [Y]es. Notice that printing all solutions may take some time.")
    val userPrintAnswer = safeStringToBoolean(scala.io.StdIn.readLine())

    if(userPrintAnswer) {
      result.foreach(println)
    }
  }

  def measureSolutionFindingTime[R](function: => R):R = {
    val t0 = System.nanoTime()
    val result = function
    val t1 = System.nanoTime()
    val totalRunningTime = (t1 - t0) / 1000000000.0
    println("Finding all solutions took: "+ totalRunningTime + " seconds.")

    result
  }
}
