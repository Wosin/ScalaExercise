import model._

import scala.annotation.tailrec

object Boot extends App {
  override def main(args: Array[String]): Unit = {
    val t0 = System.nanoTime();
    val result = putAllPiecesOnFields(Seq(Rook, Rook, Knight, Knight, Knight, Knight).sortBy(_.sortingPriority), Set(ChessBoard(4,4)))
    val t1 = System.nanoTime();
    println("TIME TOOK:" + (t1-t0)/1000000000.0)
    println("RESULTS", result.size)
  }

  @tailrec
  def putAllPiecesOnFields(piecesLeftToPut: Seq[ChessPiece], precalculatedBoards: Set[ChessBoard]): Set[ChessBoard] = {
    piecesLeftToPut match {
      case Nil => precalculatedBoards
      case piece :: tail => {
        val boardsForCurrentPiece = precalculatedBoards.flatMap(b => calculateAllPositionsForPiece(b,piece))
        putAllPiecesOnFields(tail, boardsForCurrentPiece)
      }
    }
  }

  def calculateAllPositionsForPiece(chessBoard: ChessBoard, piece: ChessPiece): Set[ChessBoard] =
    for {
      field:ChessField <- chessBoard.freeFields
      precalculatedBoards <- findFieldForPiece(chessBoard, field, piece)
    } yield precalculatedBoards


  def findFieldForPiece(currentBoard:ChessBoard, fieldToTake: ChessField, pieceToPut: ChessPiece): Option[ChessBoard] = {
    val piecesOnFields = currentBoard.piecesOnFields
    val emptyFields = currentBoard.freeFields
    if(!piecesOnFields.keySet.exists(fieldToCheck => pieceToPut.isThreathening(fieldToCheck, fieldToTake))) {
      val pieceOnField = fieldToTake -> pieceToPut
      Some(
        ChessBoard(
          currentBoard.width,
          currentBoard.height,
          emptyFields.filterNot(fieldToCheck => pieceToPut.isThreathening(fieldToCheck,fieldToTake)),
          piecesOnFields + pieceOnField)
      )
    } else {
      None
    }
  }
}
