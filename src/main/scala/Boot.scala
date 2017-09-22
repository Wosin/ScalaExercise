import javax.management.MatchQueryExp

import model._
import solving.SolutionFinder

import scala.util.Try

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

    val t0 = System.nanoTime()
    val result = SolutionFinder.findSolutionForGivenBoardSize(pieces, width, height)
    val t1 = System.nanoTime()

    println("TIME TOOK:" + (t1 - t0) / 1000000000.0)
    println("RESULTS", result.size)
    result.take(10).foreach(println)
  }

  def safeStringToInt(numberString: String) = {
      Try(numberString.toInt).toOption
  }

  def safeStringToPieces(piecesString: String): Option[Seq[ChessPiece]] = {
    Try {
      piecesString
        .split(" ")
        .map(pieceLetter => PiecesMatcher.pieceFromString(pieceLetter))
        .toList
    }.toOption
  }
  
}
