import com.AtlasLibrary.AtlasGameHistory;
import com.AtlasLibrary.AtlasPlayer;

class AtlasGameHistoryTest {

    public static void runAllTests() throws TestRunner.AssertException{
        recordMoveTest();
    }
    public static boolean recordMoveTest() throws TestRunner.AssertException {
        AtlasGameHistory atlasGameHistory = new AtlasGameHistory();
        AtlasPlayer player=new AtlasPlayer(1);
        String word="Khammam";
        atlasGameHistory.recordMove(player, word);
        TestRunner.AssertEquals(atlasGameHistory.getPlayerOnTurn(0),player);
        TestRunner.AssertEquals(atlasGameHistory.getActionOnTurn(0),word);
        return true;
    }
}