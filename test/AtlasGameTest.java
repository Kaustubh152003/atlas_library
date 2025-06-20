package test;
import main.AtlasGame;
import static test.TestRunner.*;
public class AtlasGameTest {
    public static void runAllTests() throws Exception{
        AtlasGameCreationTest();
    }
    public static boolean AtlasGameCreationTest() throws Exception{
        int gameId = 1;
        int maxSize = 100;
        AtlasGame atlasGame = new AtlasGame(gameId,maxSize);
        AssertNotNull(atlasGame.getAtlasPlaceValidator());
        AssertEquals(atlasGame.getGameId(), gameId);
        AssertEquals(atlasGame.getMaxSize(), maxSize);
        return true;
    }
}
