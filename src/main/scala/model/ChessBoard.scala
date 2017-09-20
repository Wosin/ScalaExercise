package model


object ChessBoard {
  def apply(width:Int, height:Int) : ChessBoard = {
    val chessFields = for {
      x <- 1 to width
      y <- 1 to height
    } yield ChessField(x, y)

    new ChessBoard(chessFields.toSet)
  }
}
case class ChessBoard(freeFields: Set[ChessField], piecesOnFields: Map[ChessField, ChessPiece] = Map()) {

}
