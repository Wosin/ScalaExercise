package casting

import model.{ChessPiece, PiecesMatcher}

import scala.util.Try

object TypeCasting {
  def safeStringToInt(numberString: String) = {
    Try(numberString.toInt).toOption
  }

  def safeStringToBoolean(booleanString: String) = {
    booleanString.toUpperCase match {
      case "YES" => true
      case "Y" => true
      case _ => false
    }
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
