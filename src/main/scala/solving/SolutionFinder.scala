package solving

class SolutionFinder

  import model._

  import scala.annotation.tailrec

  object SolutionFinder {
    def findSolutionForGivenBoardSize(piecesToPut: Seq[ChessPiece], width:Int, height: Int) = {
      val boardWithGivenSize = ChessBoard(width, height)
      putAllPiecesOnFields(piecesToPut.sortBy(_.sortingPriority), Set(boardWithGivenSize))
    }

    @tailrec
    private final def putAllPiecesOnFields(piecesLeftToPut: Seq[ChessPiece], precalculatedBoards: Set[ChessBoard]): Set[ChessBoard] = {
      piecesLeftToPut match {
        case Nil => precalculatedBoards
        case piece :: tail => {
          val boardsForCurrentPiece = precalculatedBoards.flatMap(b => calculateAllPositionsForPiece(b,piece))
          putAllPiecesOnFields(tail, boardsForCurrentPiece)
        }
      }
    }

    private def calculateAllPositionsForPiece(chessBoard: ChessBoard, piece: ChessPiece): Set[ChessBoard] =
      for {
        field:ChessField <- chessBoard.freeFields
        precalculatedBoards <- findFieldForPiece(chessBoard, field, piece)
      } yield precalculatedBoards


    private def findFieldForPiece(currentBoard:ChessBoard, fieldToTake: ChessField, pieceToPut: ChessPiece): Option[ChessBoard] = {
      val piecesOnFields = currentBoard.piecesOnFields
      val emptyFields = currentBoard.freeFields
      if(!piecesOnFields.keySet.exists(fieldToCheck => pieceToPut.isThreathening(fieldToCheck, fieldToTake))) {
        val pieceOnField = fieldToTake -> pieceToPut
        Some(
          ChessBoard(
            currentBoard.width,
            currentBoard.height,
            emptyFields.filterNot(fieldToCheck => pieceToPut.isThreathening(fieldToCheck,fieldToTake)),
            piecesOnFields + pieceOnField
          )
        )
      } else {
        None
      }
    }
  }


