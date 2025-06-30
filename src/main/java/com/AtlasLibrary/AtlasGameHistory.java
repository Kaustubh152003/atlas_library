package com.AtlasLibrary;

import java.util.ArrayList;
import java.util.List;


public class AtlasGameHistory{
    private List<Integer> playerTurns;
    private List<String> playerActions;
    private int turnSize;

    public List<Integer> getPlayerTurns() {
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
    }
    public boolean recordMove(int player,String word)
    {
        playerTurns.add(player);
        playerActions.add(word);
        turnSize++;
        return true;
    }
    public int getPlayerOnTurn(int turn)
    {
        if(turn<turnSize)
        return playerTurns.get(turn);
        else
        return -1;
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
            System.out.printf("Turn %d : %d played %s\n",j+1,playerTurns.get(j),playerActions.get(j));
            j++;
        }
    }

    
}