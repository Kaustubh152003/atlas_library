package test;
import main.AtlasGameHistory;
import static test.TestRunner.AssertEquals;


class AtlasGameHistoryTest {

    public static void runAllTests() throws TestRunner.AssertBooleansException{
        recordMoveTest();
    }
    public static boolean recordMoveTest() throws TestRunner.AssertBooleansException {
        AtlasGameHistory atlasGameHistory = new AtlasGameHistory();
        int player=1;
        String word="Khammam";
        atlasGameHistory.recordMove(player, word);
        AssertEquals(atlasGameHistory.getPlayerOnTurn(0),player);
        AssertEquals(atlasGameHistory.getActionOnTurn(0),word);
        return true;
    }
}