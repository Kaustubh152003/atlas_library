import java.util.Set;
import java.util.TreeSet;

public class AtlasWordValidator {
    private Set<String> validWords;
    private Set<String> remainingWords; // contains only lowercase words
    private Set<String> completedWords;
    public AtlasWordValidator() {
        initializeValidWords();
        this.remainingWords = new TreeSet<String>(validWords);
        this.completedWords = new TreeSet<String>();
    }
    private void initializeValidWords(){
        
    }
    private String lowerCase(String word)
    {
        if(word!=null)
        return word.toLowerCase();
        else
        return null;
    }
    public Boolean validate(String word)
    {
        String lowerCaseWord = lowerCase(word);
        return remainingWords.contains(lowerCaseWord);
    }
    public Boolean validateAndPlay(String word)
    {
        String lowerCaseWord = lowerCase(word);
        if(remainingWords.contains(lowerCaseWord))
        {
            remainingWords.remove(lowerCaseWord);
            completedWords.add(lowerCaseWord);
            return true;
        }
        else
        return false;
    }
    public Set<String> getCompletedWords()
    {
        return new TreeSet<>(this.completedWords);
    }


}