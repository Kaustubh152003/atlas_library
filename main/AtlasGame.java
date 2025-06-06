package main;
import java.util.Set;
import java.util.TreeSet;
public class AtlasGame {
    int gameId;
    int players[];
    int playersSize;
    int maxSize;
    int currentPlayerIndex;
    char currentLetter;
    int status;
    public Set<String> completedWords;
    AtlasPlaceValidator atlasPlaceValidator;
    public AtlasGame(int gameId,AtlasPlaceValidator atlasPlaceValidator,int maxSize){
        this.gameId=gameId;
        this.atlasPlaceValidator=atlasPlaceValidator;
        this.status=0;
        this.players=new int[maxSize];
        this.maxSize=maxSize;
        this.completedWords = new TreeSet<>();
        this.playersSize=0;
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
        if(this.status==0 && getPlayerIndex(player)==-1 && playersSize<maxSize)
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
        currentPlayerIndex=0;
        currentLetter='s';
        this.status=1;
        return true;
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
    public int playTurn(int player,String word)
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
                this.currentLetter=lastLetter(lowerCaseWord);
                return moveToNextPlayer();
            }
            else
            {
                if(!playerTurn)
                {
                    return -2;
                }
                else if(!validStartingLetter)
                {
                    return -3;
                }
                else if(!validWord)
                {
                    return -4;
                }
                else if(!notCompletedWord)
                {
                    return -5;
                }
                else
                {
                    return -6;
                }
            }
        }
        else
        return -1;
    }
    public int skipTurn(int player)
    {
        if(getCurrentPlayer()==player)
        {
            return moveToNextPlayer();
        }
        else
        return -1;
    }

}
