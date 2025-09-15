import com.AtlasLibrary.AtlasPlayer;

public class AtlasPlayerTest {

    public static void runAllTests() throws TestRunner.AssertException{
        playerInitTest();
        playerEqualsTest();
    }
    public static boolean playerInitTest() throws TestRunner.AssertException {
        int playerId =1;
        String playerName = "TestPlayer";
        AtlasPlayer atlasPlayer = new AtlasPlayer(playerId, playerName);
        TestRunner.AssertEquals(atlasPlayer.getPlayerId(),playerId);
        TestRunner.AssertEquals(atlasPlayer.getPlayerName(),playerName);
        return true;
    }
    public static boolean playerEqualsTest() throws TestRunner.AssertException {
        AtlasPlayer atlasPlayer1 = new AtlasPlayer(1, "Pammi");
        AtlasPlayer atlasPlayer2 = new AtlasPlayer(2, "Pammi");
        AtlasPlayer atlasPlayer3 = new AtlasPlayer(1, "DiffName");
        TestRunner.AssertEquals(atlasPlayer1.equals(atlasPlayer2),false);
        System.out.println(atlasPlayer1.getPlayerId()==atlasPlayer3.getPlayerId());
        System.out.println(atlasPlayer3.getPlayerId());
        TestRunner.AssertEquals(AtlasPlayer.equals(atlasPlayer1, atlasPlayer3),true);
        return true;
    }
}
