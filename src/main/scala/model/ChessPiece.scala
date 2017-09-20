package model


sealed trait ChessPiece{

  def sortingPriority: Int
}

case object King extends ChessPiece {

  override def sortingPriority = 5
}

case object Queen extends ChessPiece {

  override def sortingPriority = 1
}

case object Rook extends ChessPiece {

  override def sortingPriority = 2
}

case object Bishop extends ChessPiece {

  override def sortingPriority = 3
}

case object Knight extends ChessPiece {
  
  override def sortingPriority = 4
}

