package com.AtlasLibrary;
public class AtlasPlayer extends Player{

    public AtlasPlayer(int playerId,String playerName) {
        super(playerId,playerName);
    }
    public AtlasPlayer(int playerId) {
        super(playerId);
    }
    public AtlasPlayer(Player player){
        super(player);
    }
    
}