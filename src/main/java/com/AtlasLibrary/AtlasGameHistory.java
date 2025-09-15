package com.AtlasLibrary;

import java.util.ArrayList;
import java.util.List;


public class AtlasGameHistory{
    private List<Player> playerTurns;
    private List<String> playerActions;
    private int turnSize;

    public List<Player> getPlayerTurns() {
        return playerTurns;
    }
    public List<String> getPlayerActions() {
        return playerActions;
    }
    public int getTurnSize() {
        return turnSize;
    }
    public AtlasGameHistory() {
        playerTurns=new ArrayList<>();
        playerActions=new ArrayList<>();
        turnSize =0;
    }
    public AtlasGameHistory(AtlasGameHistory atlasGameHistory) {
        this.playerTurns=new ArrayList<>(atlasGameHistory.getPlayerTurns());
        this.playerActions=new ArrayList<>(atlasGameHistory.getPlayerActions());
        this.turnSize = atlasGameHistory.getTurnSize();
    }
    public boolean recordMove(Player player,String word)
    {
        playerTurns.add(player);
        playerActions.add(word);
        turnSize++;
        return true;
    }
    public Player getPlayerOnTurn(int turn)
    {
        if(turn<turnSize)
        return playerTurns.get(turn);
        else
        return null;
    }
    public String getActionOnTurn(int turn)
    {
        if(turn<turnSize)
        return playerActions.get(turn);
        else
        return null;
    }
    public void printAtlasGameHistory()
    {
        int j=0;
        while(j<turnSize)
        {
            System.out.printf("Turn %d : %d played %s\n",j+1,playerTurns.get(j).getPlayerId(),playerActions.get(j));
            j++;
        }
    }

    
}