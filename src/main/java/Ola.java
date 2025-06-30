
import com.AtlasLibrary.AtlasGame;
import com.AtlasLibrary.AtlasPlaceValidator;

public class Ola {
    public static void printAtlasGameDetails(AtlasGame atlasGame) {
        System.out.println("gameId: " + atlasGame.getGameId());
        System.out.print("players: ");
        int[] players = atlasGame.getPlayers();
        int playersSize = atlasGame.getPlayersSize();
        if (players != null) {
            for (int i = 0; i < playersSize; i++) {
                System.out.print(players[i]);
                if (i < playersSize - 1) System.out.print(", ");
            }
        }
        System.out.println();
        System.out.println("playersSize: " + playersSize);
        System.out.println("maxSize: " + atlasGame.getMaxSize());
        System.out.println("currentPlayerIndex: " + atlasGame.getCurrentPlayerIndex());
        System.out.println("currentLetter: " + atlasGame.getCurrentLetter());
        System.out.println("status: " + atlasGame.getStatus());
        System.out.println("completedWords: " + atlasGame.getCompletedWords());
        System.out.println("atlasPlaceValidator: " + atlasGame.getAtlasPlaceValidator());
        System.out.printf("\n\n");
    }
    public static void main(String[] args) throws Exception {
        AtlasPlaceValidator atlasPlaceValidator= new AtlasPlaceValidator();
        String place = "Khammam";
        System.out.print(atlasPlaceValidator.validate(place));

        System.out.printf("\n\nTesting AtlasGame\n\n\n");

        AtlasGame atlasGame = new AtlasGame(1,atlasPlaceValidator,100);
        atlasGame.addPlayer(1);
        atlasGame.addPlayer(2);
        atlasGame.addPlayer(3);
        atlasGame.addPlayer(4);
        atlasGame.removePlayer(4);

        printAtlasGameDetails(atlasGame);

        atlasGame.startGame();
        atlasGame.playTurn(1, "Singapore");
        atlasGame.playTurn(2, "England");
        atlasGame.playTurn(3, "delhi");
        // atlasGame.playTurn(4, "Ireland");
        atlasGame.playTurn(1, "Ireland");
        atlasGame.playTurn(2, "denmark");
        atlasGame.playTurn(3, "khammam");
        atlasGame.playTurn(1, "Malaysia");
        atlasGame.removePlayer(2);
        // atlasGame.playTurn(2, "asia");
        atlasGame.playTurn(3, "africa");


        printAtlasGameDetails(atlasGame);
        atlasGame.getGameHistory().printAtlasGameHistory();


    }
}