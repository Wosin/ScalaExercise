import model._
import org.scalatest.{FlatSpec, Matchers}
import solving.SolutionFinder

class SolutionFindingTests  extends FlatSpec with Matchers {

  "Solution Finder" should "find 4 different solutions for board 3x3 and pieces: King, King, Rook" in {
    val pieces = Seq(King, King, Rook)
    val results = SolutionFinder.findSolutionForGivenBoardSize(pieces, 3 , 3)

    results should have size(4)
    results foreach(result => result.piecesOnFields.values should contain allElementsOf(pieces))
  }

  "Solution Finder" should "find 4 different solutions for board 4x4 and pieces: Rook, Rook, Knight, Knight, Knight, Knight" in {
    val pieces = Seq(Rook, Rook, Knight, Knight, Knight, Knight)
    val results = SolutionFinder.findSolutionForGivenBoardSize(pieces, 4 , 4)

    results should have size(8)
    results foreach(result => result.piecesOnFields.values should contain allElementsOf(pieces))
  }
}
