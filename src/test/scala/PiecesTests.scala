import model._
import org.scalatest.{FlatSpec, Matchers}

class PiecesTests extends FlatSpec with Matchers{
  "List of Pieces" should "be properly sorted accoring to priorities" in  {
    val listToSort = List(King, Queen, Bishop, King, Rook, Queen, Bishop, Knight, King)
    val sortedList = List(Queen, Queen, Rook, Bishop, Bishop, Knight, King, King , King)
    listToSort.sortBy(_.sortingPriority) should equal(sortedList)
}
}
