package com.AtlasLibrary;

import java.util.Set;
import java.util.TreeSet;
public class AtlasGame {
    private int gameId;
    private int players[];
    private int playersSize;
    private int maxSize;
    private int currentPlayerIndex;
    private char currentLetter;
    private int status;
    private Set<String> completedWords;
    private AtlasPlaceValidator atlasPlaceValidator;
    private AtlasGameHistory gameHistory;

    public int getGameId() {
        return gameId;
    }
    public int[] getPlayers() {
        return players;
    }
    public int getPlayersSize() {
        return playersSize;
    }
    public int getMaxSize() {
        return maxSize;
    }
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }
    public char getCurrentLetter() {
        return currentLetter;
    }
    public int getStatus() {
        return status;
    }
    public Set<String> getCompletedWords() {
        return completedWords;
    }
    public AtlasPlaceValidator getAtlasPlaceValidator() {
        return atlasPlaceValidator;
    }
    public AtlasGameHistory getGameHistory() {
        return gameHistory;
    }
    public AtlasGame(int gameId,AtlasPlaceValidator atlasPlaceValidator,int maxSize){
        if(atlasPlaceValidator != null)
        {
            this.gameId=gameId;
            this.atlasPlaceValidator=atlasPlaceValidator;
            this.maxSize=maxSize;
            initialize();
        }
        else
        {
            this.gameId=gameId;
            this.maxSize=maxSize;
            this.atlasPlaceValidator=AtlasPlaceValidatorProvider.getAtlasPlaceValidator();
            initialize();
        }
    }
    public AtlasGame(int gameId,int maxSize){
        this.gameId=gameId;
        this.maxSize=maxSize;
        this.atlasPlaceValidator=AtlasPlaceValidatorProvider.getAtlasPlaceValidator();
        initialize();
    }
    private void initialize()
    {
        this.status=0;
        this.players=new int[maxSize];
        this.completedWords = new TreeSet<>();
        this.playersSize=0;
        this.gameHistory=new AtlasGameHistory();
    }
    private int getPlayerIndex(int player)
    {
        int j=0;
        while(j<playersSize && players[j]!=player)
        j++;
        if(j==playersSize)
        return -1;
        else
        return j; 

    }
    public boolean addPlayer(int player)
    {
        if(this.status==0 && getPlayerIndex(player)==-1 && playersSize<maxSize && player>0)
        {
            players[playersSize]=player;
            playersSize++;
            return true;
        }
        else
        return false;
    }
    public boolean removePlayer(int player)
    {
        if(this.status==0 || this.status==1)
        {
            if(this.status==1 && player==getCurrentPlayer())
            {
                moveToNextPlayer();
            }
            int j=getPlayerIndex(player);
            while(j<playersSize-1)
            {
                players[j]=players[j+1];
                j++;
            }
            playersSize--;
            if(playersSize==0)
            {
                stopGame();
            }
            return true;
        }
        else
        {
            return false;
        }
    }
    public int moveToNextPlayer()
    {
        currentPlayerIndex = (currentPlayerIndex+1)%(playersSize);
        return getCurrentPlayer();
    }
    public int getCurrentPlayer()
    {
        if(this.status==1)
        return players[currentPlayerIndex];
        else
        return -1;
    }
    public boolean startGame()
    {
        char startingLetter = 's';
        return startGame(startingLetter);
    }
    public boolean startGame(char startingLetter)
    {
        if(this.status==0)
        {
            currentPlayerIndex=0;
            currentLetter=startingLetter;
            this.status=1;
            return true;
        }
        else
        return false;
    }
    public Boolean stopGame()
    {
        if(this.status==1)
        {
            this.status=2;
            return true;
        }
        else
        return false;
    }
    private String lowerCase(String word)
    {
        if(word!=null)
        return word.toLowerCase();
        else
        return null;
    }
    private char lastLetter(String word)
    {
        if(word!=null)
        {
            return word.charAt(word.length()-1);
        }
        else
        return 0;
    }
    public int playTurn(int player,String word) throws AtlasExceptions.AtlasPlayException
    {
        if(word != null && this.status==1)
        {
            String lowerCaseWord = lowerCase(word);
            boolean playerTurn = getCurrentPlayer()==player;
            boolean validStartingLetter = lowerCaseWord.charAt(0)==currentLetter;
            boolean validWord = atlasPlaceValidator.validate(lowerCaseWord);
            boolean notCompletedWord = !completedWords.contains(lowerCaseWord);
            if(playerTurn && validStartingLetter && validWord && notCompletedWord)
            {
                completedWords.add(lowerCaseWord);
                gameHistory.recordMove(player, lowerCaseWord);
                this.currentLetter=lastLetter(lowerCaseWord);
                return moveToNextPlayer();
            }
            else
            {
                if (!playerTurn) {
                    throw new AtlasExceptions.AtlasPlayException("It's not the player's turn.");
                }
                else if (!validStartingLetter) {
                    throw new AtlasExceptions.AtlasPlayException("Word does not start with the required letter.");
                }
                else if (!validWord) {
                    throw new AtlasExceptions.AtlasPlayException("Word is not a valid place.");
                }
                else if (!notCompletedWord) {
                    throw new AtlasExceptions.AtlasPlayException("Word has already been used.");
                }
                throw new AtlasExceptions.AtlasPlayException("Invalid play.");
            }
        }
        else
        throw new AtlasExceptions.AtlasPlayException("The Game hasnt started or the word is null.");
    }
    public int skipTurn(int player) throws AtlasExceptions.AtlasPlayException
    {
        if(getCurrentPlayer()==player)
        {
            gameHistory.recordMove(player, null);
            return moveToNextPlayer();
        }
        else
        throw new AtlasExceptions.AtlasPlayException("It's not the player's turn.");
    }

}
