package com.AtlasLibrary;

import java.util.HashMap;
import java.util.Map;

import com.AtlasLibrary.Constants.GameStatus;
import com.AtlasLibrary.Constants.PlayerStatus;


class ResponseMessages{
    public static final String AUTHENTICATION_FAILED = "Player Authentication Failed";
    public static final String GAME_STARTED = "Game has already been started";
    public static final String PLAYER_EXISTS = "Player already exists";
}
class PlayerMetaData{
    private GamePlayer gamePlayer;
    private AtlasPlayer atlasPlayer;
    private PlayerStatus playerStatus;
    private int skipsAvailable;
    public PlayerMetaData(GamePlayer player,int skipsAvailable){
        this.gamePlayer = new GamePlayer(player);
        this.playerStatus = PlayerStatus.NOT_READY;
        this.atlasPlayer = new AtlasPlayer(player);
        this.skipsAvailable = skipsAvailable;

    }
    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }
    public AtlasPlayer getAtlasPlayer() {
        return atlasPlayer;
    }
    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }
    public int getSkipsAvailable() {
        return skipsAvailable;
    }
    public boolean authenticateGamePlayer(GamePlayer gamePlayer){
        return this.gamePlayer.equals(gamePlayer);
    }
    public boolean setPlayerStatus(PlayerStatus playerStatus){
        this.playerStatus = playerStatus;
        return true;
    }
    public boolean areSkipsAvailable(){
        return skipsAvailable>0;
    }
    public boolean useSkip(){
        if(skipsAvailable > 0){
            this.skipsAvailable--;
            return true;
        }
        else{
            return false;
        }
    }

}
public class AtlasOrchestrator {
    private AtlasGame atlasGame;
    private Map<Integer,PlayerMetaData> playerIDMap;
    private int skipsProvided;
    private int timerForTurnInSeconds;
    private boolean timerEnabled;
    public AtlasOrchestrator(int skipsProvided, int timerForTurnInSeconds,boolean timerEnabled, int maxPlayers) {
        this.atlasGame=new AtlasGame(1,maxPlayers);
        this.playerIDMap = new HashMap<>();
        this.skipsProvided=skipsProvided;
        this.timerForTurnInSeconds=timerForTurnInSeconds;
        this.timerEnabled=timerEnabled;
    }
    
    private boolean authenticateGamePlayer(GamePlayer gamePlayer){
        return playerIDMap.containsKey(gamePlayer.getPlayerId()) && 
                playerIDMap.get(gamePlayer.getPlayerId()).authenticateGamePlayer(gamePlayer);
    }
    private PlayerMetaData authenticateAndGetMetaData(GamePlayer gamePlayer){
        if(gamePlayer!=null)
        {
            PlayerMetaData metaData = playerIDMap.get(gamePlayer.getPlayerId());
            if(metaData!=null && metaData.authenticateGamePlayer(gamePlayer)){
                return metaData;
            }
            else
            return null;
        }
        else
        return null;
    }

    public boolean isWordCompleted(String word){
        return atlasGame.isWordCompleted(word);
    }
    
    public ActionResponse registerPlayer(GamePlayer gamePlayer){
        PlayerMetaData playerMetaData = new PlayerMetaData(gamePlayer,this.skipsProvided);
        if(playerIDMap.containsKey(gamePlayer.getPlayerId())){
            return new ActionResponse(false, "Player Already Exists");
        }
        else
        {
            ActionResponse response = atlasGame.addPlayer(playerMetaData.getAtlasPlayer());
            if(response.isActionSuccess()){
                playerIDMap.put(gamePlayer.getPlayerId(), playerMetaData);
            }
            return response;
        }
    }
    public ActionResponse unRegisterPlayer(GamePlayer gamePlayer){
        PlayerMetaData metaData = authenticateAndGetMetaData(gamePlayer);
        if(metaData!=null){
            playerIDMap.remove(gamePlayer.getPlayerId());
            return atlasGame.removePlayer(metaData.getAtlasPlayer());
        }
        else {
            return ActionResponse.getFailedActionResponse(ResponseMessages.AUTHENTICATION_FAILED);
        }
    }
    public ActionResponse playerReadyUp(GamePlayer gamePlayer){
        PlayerMetaData metaData = authenticateAndGetMetaData(gamePlayer);
        if(metaData!=null){
            if(atlasGame.getStatus()==GameStatus.PRE_GAME){
                boolean isSuccess = metaData.setPlayerStatus(PlayerStatus.READY);
                return ActionResponse.getActionResponseFromBoolean(isSuccess);
            }
            else{
                return ActionResponse.getFailedActionResponse("Game has already been started");
            }
        }
        else{
            return ActionResponse.getFailedActionResponse(ResponseMessages.AUTHENTICATION_FAILED);
        }
    }
    public ActionResponse playerUnReady(GamePlayer gamePlayer){
        PlayerMetaData metaData = authenticateAndGetMetaData(gamePlayer);
        if(metaData!=null){
            if(atlasGame.getStatus()==GameStatus.PRE_GAME){
                boolean isSuccess = metaData.setPlayerStatus(PlayerStatus.NOT_READY);
                return ActionResponse.getActionResponseFromBoolean(isSuccess);
            }
            else{
                return ActionResponse.getFailedActionResponse("Game has already been started");
            }
        }
        else{
            return ActionResponse.getFailedActionResponse(ResponseMessages.AUTHENTICATION_FAILED);
        }
    }

    public boolean checkIfAllPlayersAreReady(){
        return true;
    }
    public ActionResponse startGame(){
        if(checkIfAllPlayersAreReady()){
            return atlasGame.startGame();
        }
        else{
            return ActionResponse.getFailedActionResponse("All players are not ready");
        }
    }
    public GameStatus getGameStatus(){
        return atlasGame.getStatus();
    }
    // public boolean stopGame(){
        
    // }

    private ActionResponse kickPlayer(GamePlayer gamePlayer){
        return unRegisterPlayer(gamePlayer);
    }
    public ActionResponse abandonGame(GamePlayer gamePlayer){
        return unRegisterPlayer(gamePlayer);
    }

    public AtlasPlayer getTurn(){
        return atlasGame.getCurrentPlayer();
    }
    public char getCurrentLetter(){
        return atlasGame.getCurrentLetter();
    }

    public AtlasGameHistory getGameHistory(){
        return atlasGame.getGameHistory();
    }

    public ActionResponse play(GamePlayer gamePlayer,String place){
        PlayerMetaData metaData = authenticateAndGetMetaData(gamePlayer);
        if(metaData!=null){
            return atlasGame.playTurn(metaData.getAtlasPlayer(), place);
        }
        else{
            return ActionResponse.getFailedActionResponse(ResponseMessages.AUTHENTICATION_FAILED);
        }
    }
    public ActionResponse skipTurn(GamePlayer gamePlayer){
        PlayerMetaData metaData = authenticateAndGetMetaData(gamePlayer);
        if(metaData!=null){
            boolean skipsAvailable = metaData.areSkipsAvailable();
            if(skipsAvailable){
                ActionResponse response = atlasGame.skipTurn(metaData.getAtlasPlayer());
                if(response.isActionSuccess()){
                    metaData.useSkip();
                }
                return response;
            }
            else{
                ActionResponse response = kickPlayer(gamePlayer);
                if(response.isActionSuccess()){
                    return ActionResponse.getSuccessfulActionResponse("Player doesnt have skips remaining. Player has been kicked successfully");
                }
                else{
                    return ActionResponse.getFailedActionResponse("Failed to kick the player");
                }
            }
        }
        else{
            return ActionResponse.getFailedActionResponse(ResponseMessages.AUTHENTICATION_FAILED);
        }
    }
    
}
