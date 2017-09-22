package model


object ChessBoard {
  def apply(width:Int, height:Int) : ChessBoard = {
    val chessFields = for {
      x <- 1 to width
      y <- 1 to height
    } yield ChessField(x, y)

    new ChessBoard(width, height, chessFields.toSet)
  }
}
case class ChessBoard(width: Int, height: Int, freeFields: Set[ChessField], piecesOnFields: Map[ChessField, ChessPiece] = Map()) {
  override def toString: String = {
   val string =  for {
    y <- 1 to height
    x <- 1 to width

  } yield {
     val str = piecesOnFields.find(
       entry => entry._1.x == x && entry._1.y == y
     ) match {
       case Some(piece) => piece._2.toString()
       case _ => " "
     }

     if(x == width){
       "|"+ str + "|\n"

     } else {
       "|" + str
     }
   }

    string.mkString
  }
}
