package com.AtlasLibrary;

 public class Player {
    int playerId;
    String playerName;
    public int getPlayerId(){
        return playerId;
    }
    public String getPlayerName(){
        return playerName;
    }
    public Player(int playerId,String playerName){
        this.playerId=playerId;
        this.playerName= playerName;
    }
    public Player(int playerId){
        this.playerId=playerId;
    }
    public Player(Player player){
        this.playerId=player.playerId;
        this.playerName = player.playerName;
    }
    public boolean equals(Player player2){
        return player2!=null && this.playerId==player2.playerId;
    }
    public static boolean equals(Player player1, Player player2){
        if(player1==null && player2==null)
        return true;
        else
        return player1!=null && player1.equals(player2);
    }
}
