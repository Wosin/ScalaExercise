import model._
import org.scalatest.{FlatSpec, Matchers}

class PiecesTests extends FlatSpec with Matchers{
  "List of Pieces" should "be properly sorted according to priorities" in  {
    val listToSort = List(King, Queen, Bishop, King, Rook, Queen, Bishop, Knight, King)
    val sortedList = List(Queen, Queen, Rook, Bishop, Bishop, Knight, King, King , King)
    listToSort.sortBy(_.sortingPriority) should equal(sortedList)
  }

  "King" should "threaten all the fields around him" in {
    val kingField = ChessField(2,2)
    val fieldsInThreat = Seq(
      ChessField(1,1), ChessField(1,2), ChessField(1,3), ChessField(2,1),ChessField(2,3), ChessField(3,1),
        ChessField(3,2), ChessField(3,3)
    )
    val nonThreatenedFields = fieldsInThreat.filterNot(field => King.isThreathening(field, kingField))

    nonThreatenedFields should have length(0)
  }

  "King" should " not threaten fields further than 1 field from him" in {
    val kingField = ChessField(2,2)
    val safeFields = Seq(
      ChessField(4,4), ChessField(4,2)
    )
    val nonThreatenedFields = safeFields.filterNot(field => King.isThreathening(field, kingField))

    nonThreatenedFields should contain theSameElementsAs safeFields
  }

  "Rook" should "threaten all fieds with same row or column" in {
    val rookField = ChessField(2,2)
    val safeFields = Seq(
      ChessField(2,1), ChessField(1,2),ChessField(2,3), ChessField(3,2)
    )
    val nonThreatenedFields = safeFields.filterNot(field => Rook.isThreathening(field, rookField))

    nonThreatenedFields should have length(0)
  }

  "Rook" should "not threaten all fieds with different row or column" in {
    val rookField = ChessField(2,2)
    val safeFields = Seq(
      ChessField(1,1), ChessField(3,3),ChessField(1,3), ChessField(3,1)
    )
    val nonThreatenedFields = safeFields.filterNot(field => Rook.isThreathening(field, rookField))

    nonThreatenedFields should contain allElementsOf safeFields
  }

  "Bishop" should "threaten all fields that are on its diagonal" in {
    val bishopField = ChessField(2,2)
    val safeFields = Seq(
      ChessField(1,1), ChessField(3,3),ChessField(1,3), ChessField(3,1)
    )
    val nonThreatenedFields = safeFields.filterNot(field => Bishop.isThreathening(field, bishopField))

    nonThreatenedFields should have size(0)
  }

  "Bishop" should "not threaten fields not on its diagonal" in {
    val bishopField = ChessField(2,2)
    val safeFields = Seq(
      ChessField(2,1), ChessField(1,2),ChessField(3,2), ChessField(2,3)
    )
    val nonThreatenedFields = safeFields.filterNot(field => Bishop.isThreathening(field, bishopField))

    nonThreatenedFields should contain allElementsOf safeFields
  }

  "Knight" should "threathen fields that it is able to reach" in {
    val bishopField = ChessField(2,2)
    val safeFields = Seq(
      ChessField(4,1), ChessField(1,4),ChessField(3,4), ChessField(4,3)
    )
    val nonThreatenedFields = safeFields.filterNot(field => Knight.isThreathening(field, bishopField))

    nonThreatenedFields should have size(0)
  }

  "Knight" should "not threathen fields that it is not able to reach" in {
    val bishopField = ChessField(2,2)
    val safeFields = Seq(
      ChessField(2,1), ChessField(1,1),ChessField(1,2), ChessField(3,2), ChessField(2,3)
    )
    val nonThreatenedFields = safeFields.filterNot(field => Knight.isThreathening(field, bishopField))

    nonThreatenedFields should contain allElementsOf safeFields
  }

  "Queen" should "threaten all fields in its diagonal or with same row or column" in {
    val bishopField = ChessField(2,2)
    val safeFields = Seq(
      ChessField(2,1), ChessField(1,2),ChessField(2,1), ChessField(3,3), ChessField(1,1)
    )
    val nonThreatenedFields = safeFields.filterNot(field => Queen.isThreathening(field, bishopField))

    nonThreatenedFields should have size(0)
  }

  "Queen" should  " not threaten fields not in its diagonal or with same row or column" in {
    val bishopField = ChessField(2,2)
    val safeFields = Seq(
      ChessField(4,3), ChessField(3,4),ChessField(4,1), ChessField(1,4)
    )

    val nonThreatenedFields = safeFields.filterNot(field => Queen.isThreathening(field, bishopField))

    nonThreatenedFields should contain allElementsOf safeFields
  }
}
