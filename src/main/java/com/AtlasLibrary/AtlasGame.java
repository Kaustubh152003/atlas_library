package com.AtlasLibrary;

import java.util.Set;
import java.util.TreeSet;

import com.AtlasLibrary.Constants.GameStatus;


public class AtlasGame {
    private int gameId;
    private AtlasPlayer players[];
    private int playersSize;
    private int maxSize;
    private int currentPlayerIndex;
    private char currentLetter;
    private GameStatus gameStatus;
    private Set<String> completedWords;
    private AtlasPlaceValidator atlasPlaceValidator;
    private AtlasGameHistory gameHistory;

    public int getGameId() {
        return gameId;
    }
    public AtlasPlayer[] getPlayers() {
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
    public GameStatus getStatus() {
        return gameStatus;
    }
    public Set<String> getCompletedWords() {
        return completedWords;
    }
    public AtlasGameHistory getGameHistory() {
        return new AtlasGameHistory(gameHistory);
    }
    public AtlasPlaceValidator getAtlasPlaceValidator() {
        return atlasPlaceValidator;
    }

    
    private void initialize()
    {
        this.gameStatus=GameStatus.PRE_GAME;
        this.players=new AtlasPlayer[maxSize];
        this.completedWords = new TreeSet<>();
        this.playersSize=0;
        this.gameHistory=new AtlasGameHistory();
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

    private int getPlayerIndex(AtlasPlayer player)
    {
        int j=0;
        while(j<playersSize && players[j].equals(player)==false){
        j++;
        }
        if (j == playersSize) 
        {
          return -1;
        } 
        else 
        {
          return j;
        }

    }
    private ActionResponse moveToNextPlayer()
    {
        currentPlayerIndex = (currentPlayerIndex+1)%(playersSize);
        return ActionResponse.getSuccessfulActionResponse();
    }
    
    public ActionResponse changeMaxSize(int newMaxSize){
        if(this.gameStatus==GameStatus.PRE_GAME && maxSize>=playersSize)
        {
            this.maxSize=this.maxSize+1;
            return ActionResponse.getSuccessfulActionResponse();
        }
        else
        return ActionResponse.getFailedActionResponse();
    }
    public ActionResponse addPlayer(AtlasPlayer player)
    {
        if(this.gameStatus==GameStatus.PRE_GAME && getPlayerIndex(player)==-1 && playersSize<maxSize && player!=null)
        {
            players[playersSize]=player;
            playersSize++;
            return ActionResponse.getSuccessfulActionResponse("Successfully added player into the game");
        }
        else
        return ActionResponse.getFailedActionResponse("Couldnt add player into the game ");
    }
    public ActionResponse removePlayer(AtlasPlayer player)
    {
        if(this.gameStatus!=GameStatus.ENDED)
        {
            if(this.gameStatus==GameStatus.IN_PROGRESS && AtlasPlayer.equals(player,getCurrentPlayer()))
            {
                moveToNextPlayer();
            }
            int index=getPlayerIndex(player);
            int j=index;
            if(index!=-1){
                while(j<playersSize-1)
                {
                    players[j]=players[j+1];
                    j++;
                }
                playersSize--;
                if(currentPlayerIndex>index){
                    currentPlayerIndex--;
                }
            }

            if(playersSize<=0)
            {
                stopGame();
            }
            return ActionResponse.getSuccessfulActionResponse("Player has been successfully removed");
        }
        else
        {
            return ActionResponse.getFailedActionResponse();
        }
    }
    public AtlasPlayer getCurrentPlayer()
    {
        if(this.gameStatus==GameStatus.IN_PROGRESS)
        return players[currentPlayerIndex];
        else
        return null;
    }

    public ActionResponse startGame()
    {
        char startingLetter = 's';
        return startGame(startingLetter);
    }
    public ActionResponse startGame(char startingLetter)
    {
        if(this.gameStatus==GameStatus.PRE_GAME && playersSize>0)
        {
            currentPlayerIndex=0;
            currentLetter=startingLetter;
            this.gameStatus=GameStatus.IN_PROGRESS;
            return ActionResponse.getSuccessfulActionResponse();
        }
        else
        return ActionResponse.getFailedActionResponse();
    }
    
    public ActionResponse stopGame()
    {
        if(this.gameStatus==GameStatus.IN_PROGRESS)
        {
            this.gameStatus=GameStatus.ENDED;
            return ActionResponse.getSuccessfulActionResponse();
        }
        else
        return ActionResponse.getFailedActionResponse();
    }
    public boolean isWordCompleted(String word){
        return word!=null && completedWords.contains(lowerCase(word));
    }
    public ActionResponse playTurn(AtlasPlayer player,String word)
    {
        if(word != null && this.gameStatus==GameStatus.IN_PROGRESS)
        {
            String lowerCaseWord = lowerCase(word);
            boolean playerTurn = AtlasPlayer.equals(getCurrentPlayer(),player);
            boolean validStartingLetter = lowerCaseWord.charAt(0)==currentLetter;
            boolean validWord = atlasPlaceValidator.validate(lowerCaseWord);
            boolean notCompletedWord = !completedWords.contains(lowerCaseWord);
            if(playerTurn && validStartingLetter && validWord && notCompletedWord)
            {
                completedWords.add(lowerCaseWord);
                gameHistory.recordMove(player, lowerCaseWord);
                this.currentLetter=lastLetter(lowerCaseWord);
                moveToNextPlayer();
                return ActionResponse.getSuccessfulActionResponse("Word "+lowerCaseWord + " has been played successfully by " + player.getPlayerName());
            }
            else 
            {
                if (!playerTurn) {
                    return ActionResponse.getFailedActionResponse("It's not the player's turn.");
                } else if (!validStartingLetter) {
                    return ActionResponse.getFailedActionResponse("Word does not start with the required letter.");
                } else if (!validWord) {
                    return ActionResponse.getFailedActionResponse("Word is not a valid place.");
                } else if (!notCompletedWord) {
                    return ActionResponse.getFailedActionResponse("Word has already been used.");
                } else {
                    return ActionResponse.getFailedActionResponse("Invalid play.");
                }
            }
        } else {
            return ActionResponse.getFailedActionResponse("The Game hasnt started or the word is null.");
        }
    }
    public ActionResponse skipTurn(AtlasPlayer player)
    {
        if(AtlasPlayer.equals(getCurrentPlayer(),player))
        {
            gameHistory.recordMove(player, null);
            ActionResponse response = ActionResponse.getSuccessfulActionResponse("skipped turn successfully");
            moveToNextPlayer();
            return response;
        }
        else{
            return ActionResponse.getFailedActionResponse("It's not the player's turn.");
        }
    }

}
