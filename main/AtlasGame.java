
import java.util.Set;
import main.AtlasPlaceValidator;
public class AtlasGame {
    int gameId;
    int turnTime;
    int players[];
    int playerCount;
    int turn;
    char currentLetter;
    int status;
    private Set<String> completedWords;
    AtlasPlaceValidator atlasPlaceValidator;
    public AtlasGame(int gameId,int turnTime,AtlasPlaceValidator atlasPlaceValidator){
        this.gameId=gameId;
        this.turnTime=turnTime;
        this.atlasPlaceValidator=atlasPlaceValidator;
        this.status=0;
        this.players=new int[100];
        this.playerCount=0;
    }
    public int getNextPlayer()
    {
        return 1;
    }
    public boolean start()
    {

        this.status=1;
        return true;
    }
    public boolean removePlayer(int player)
    {
        if(this.status==0)
        {
            
        }
        else if(this.status==1)
        {

        }
        else
        {
            return false;
        }
        return true;
    }
    public boolean addPlayer(int player)
    {
        if(this.status==0)
        {

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
    public int play(int player,String word)
    {
        if(word != null)
        {
            String lowerCaseWord = lowerCase(word);
            boolean playerTurn = this.turn==player;
            boolean validStartingLetter = lowerCaseWord.charAt(0)==currentLetter;
            boolean validWord = atlasPlaceValidator.validate(lowerCaseWord);
            boolean notCompletedWord = !completedWords.contains(lowerCaseWord);
            if(playerTurn && validStartingLetter && validWord && notCompletedWord)
            {
                completedWords.add(lowerCaseWord);
                this.currentLetter=lastLetter(lowerCaseWord);
                int nextPlayer = getNextPlayer();
                this.turn = nextPlayer;
                return nextPlayer;
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

}
