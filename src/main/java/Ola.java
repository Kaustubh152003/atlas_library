
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.AtlasLibrary.ActionResponse;
import com.AtlasLibrary.AtlasBot;
import com.AtlasLibrary.AtlasGame;
import com.AtlasLibrary.AtlasOrchestrator;
import com.AtlasLibrary.AtlasPlaceValidator;
import com.AtlasLibrary.AtlasPlayer;
import com.AtlasLibrary.Constants;
import com.AtlasLibrary.GamePlayer;

public class Ola {
    public static void printAtlasGameDetails(AtlasGame atlasGame) {
        System.out.println("gameId: " + atlasGame.getGameId());
        System.out.print("players: ");
        AtlasPlayer[] players = atlasGame.getPlayers();
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
        System.out.printf("\n\n");
    }
    public static void printActionResponse(ActionResponse actionResponse){
        System.out.print(actionResponse.getResponseMessage() + "\n");
    }
    public static void playAGame() throws IOException{
        
        Scanner scanner = new Scanner(System.in);
        AtlasOrchestrator atlasOrchestrator = new AtlasOrchestrator(2, 0, false, 5);
        GamePlayer player1 = new GamePlayer(1,"pammi","1");
        GamePlayer player2 = new GamePlayer(2,"krux","2");
        AtlasBot bot = new AtlasBot(3,"Botez","botkey");
        Map<Integer,GamePlayer> playerIdMap = new HashMap<>();
        ActionResponse res = atlasOrchestrator.registerPlayer(player1);
        printActionResponse(res);
        playerIdMap.put(player1.getPlayerId(), player1);

        res = atlasOrchestrator.registerPlayer(player2);
        printActionResponse(res);
        playerIdMap.put(player2.getPlayerId(), player2);

        res = atlasOrchestrator.registerPlayer(bot);
        printActionResponse(res);
        playerIdMap.put(bot.getPlayerId(), bot);

        res = atlasOrchestrator.startGame();
        printActionResponse(res);

        System.out.print("Format : ActionId Action"); //1 play, //2 skip turn , //3 enquire a word //4 abandon
        while(atlasOrchestrator.getGameStatus()==Constants.GameStatus.IN_PROGRESS){
            AtlasPlayer playerTurn = atlasOrchestrator.getTurn();
            GamePlayer gamePlayer= playerIdMap.get(playerTurn.getPlayerId());
            if(gamePlayer!=null){
                if(gamePlayer.isBot()){
                    AtlasBot botTurn = (AtlasBot)gamePlayer;
                    ActionResponse response = botTurn.playTurn(atlasOrchestrator);
                    printActionResponse(response);
                }
                else{
                    System.out.print("Its " + playerTurn.getPlayerId() + " : " + playerTurn.getPlayerName() + " turn.\n" + "Starting letter is "+atlasOrchestrator.getCurrentLetter()+"\nChoose your action\n");
                    int num=-1;
                    try{
                        num = scanner.nextInt();
                    }
                    catch(Exception e){
                        scanner.next();
                        System.err.print(e);
                        continue;
                    }
                    scanner.nextLine(); // Consume newline
                    ActionResponse response;
                    if (num == 1) {
                        System.out.print("Enter a string: ");
                        String word = scanner.nextLine();
                        response = atlasOrchestrator.play(gamePlayer, word);
                    } else if (num == 2) {
                        response=atlasOrchestrator.skipTurn(gamePlayer);
                    } 
                    else if (num==3) {
                        System.out.print("Enter a string: ");
                        String word = scanner.nextLine();
                        response = ActionResponse.getActionResponseFromBoolean(atlasOrchestrator.isWordCompleted(word));
                    }else if (num == 4) {
                        response = atlasOrchestrator.abandonGame(gamePlayer);
                    } else {
                        response=ActionResponse.getFailedActionResponse("Invalid selection\n");
                    }
                    printActionResponse(response);
                }
            }
        }
        scanner.close();
    }
    public static void main(String[] args) throws Exception {
        AtlasPlaceValidator atlasPlaceValidator= new AtlasPlaceValidator();
        String place = "Khammam";
        System.out.print(atlasPlaceValidator.validate(place));

        System.out.printf("\n\nTesting AtlasGame\n\n\n");

        AtlasPlayer player1 = new AtlasPlayer(1);
        AtlasPlayer player2 = new AtlasPlayer(2);
        AtlasPlayer player3 = new AtlasPlayer(3);
        AtlasPlayer player4 = new AtlasPlayer(4);

        AtlasGame atlasGame = new AtlasGame(1,atlasPlaceValidator,100);
        atlasGame.addPlayer(player1);
        atlasGame.addPlayer(player2);
        atlasGame.addPlayer(player3);
        atlasGame.addPlayer(player4);
        atlasGame.removePlayer(player4);

        printAtlasGameDetails(atlasGame);

        atlasGame.startGame();
        atlasGame.playTurn(player1, "Singapore");
        atlasGame.playTurn(player2, "England");
        atlasGame.playTurn(player3, "delhi");
        // atlasGame.playTurn(player4, "Ireland");
        atlasGame.playTurn(player1, "Ireland");
        atlasGame.playTurn(player2, "denmark");
        atlasGame.playTurn(player3, "khammam");
        atlasGame.playTurn(player1, "Malaysia");
        atlasGame.removePlayer(player2);
        // atlasGame.playTurn(player2, "asia");
        atlasGame.playTurn(player3, "africa");


        printAtlasGameDetails(atlasGame);
        atlasGame.getGameHistory().printAtlasGameHistory();

        playAGame();


    }
}