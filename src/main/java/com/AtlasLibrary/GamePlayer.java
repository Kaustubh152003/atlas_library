package com.AtlasLibrary;
public class GamePlayer extends Player{
    boolean hasStateChanged ;
    private final String personalKey;
    public GamePlayer(int playerId,String playerName,String personalKey) {
        super(playerId,playerName);
        this.personalKey = personalKey;
        this.hasStateChanged = false;
    }
    public GamePlayer(int playerId,String personalKey) {
        super(playerId);
        this.personalKey=personalKey;
        this.hasStateChanged = false;
    }
    public GamePlayer(GamePlayer player){
        super(player);
        this.personalKey = player.personalKey;
        this.hasStateChanged = false;
    }
    public boolean equals(GamePlayer player2){
        return player2!=null 
        && this.playerId == player2.getPlayerId()
        && this.personalKey.equals(player2.getPersonalKey());
    }
    public String getPersonalKey(){
        return personalKey;
    }
    public boolean getIsStateChanged(){
        return this.hasStateChanged;
    }
    public void notifyChange(){
        this.hasStateChanged = true;
    }
    public void acknowledgeChange(){
        this.hasStateChanged = false;
    }
    public boolean isBot(){
        return false;
    }
    
    
}