package main;
public class Ola {
    public static void printAtlasGameDetails(AtlasGame atlasGame) {
        System.out.println("gameId: " + atlasGame.gameId);
        System.out.print("players: ");
        if (atlasGame.players != null) {
            for (int i = 0; i < atlasGame.playersSize; i++) {
                System.out.print(atlasGame.players[i]);
                if (i < atlasGame.playersSize - 1) System.out.print(", ");
            }
        }
        System.out.println();
        System.out.println("playersSize: " + atlasGame.playersSize);
        System.out.println("maxSize: " + atlasGame.maxSize);
        System.out.println("currentPlayerIndex: " + atlasGame.currentPlayerIndex);
        System.out.println("currentLetter: " + atlasGame.currentLetter);
        System.out.println("status: " + atlasGame.status);
        System.out.println("completedWords: " + atlasGame.completedWords);
        System.out.println("atlasPlaceValidator: " + atlasGame.atlasPlaceValidator);
        System.out.printf("\n\n");
    }
    public static void main(String[] args) {
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
        atlasGame.playTurn(4, "Ireland");
        atlasGame.playTurn(1, "Ireland");
        atlasGame.playTurn(2, "denmark");
        atlasGame.playTurn(3, "khammam");
        atlasGame.playTurn(1, "Malaysia");

        printAtlasGameDetails(atlasGame);


    }
}