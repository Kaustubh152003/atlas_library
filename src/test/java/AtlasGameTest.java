
import com.AtlasLibrary.AtlasGame;


public class AtlasGameTest {
    public static void runAllTests() throws Exception{
        AtlasGameCreationTest();
    }
    public static boolean AtlasGameCreationTest() throws Exception{
        int gameId = 1;
        int maxSize = 100;
        AtlasGame atlasGame = new AtlasGame(gameId,maxSize);
        TestRunner.AssertNotNull(atlasGame.getAtlasPlaceValidator());
        TestRunner.AssertEquals(atlasGame.getGameId(), gameId);
        TestRunner.AssertEquals(atlasGame.getMaxSize(), maxSize);
        return true;
    }
}
