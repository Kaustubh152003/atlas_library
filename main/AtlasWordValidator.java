package main;

public class AtlasWordValidator {
    private Set<String> validWords;
    private Set<String> remainingWords; // contains only lowercase words
    private Set<String> completedWords;
    public AtlasWordValidator() {
        
    }
    private initializeValidWords(){
        
    }
    
    private String lowerCase(String word)
    {
        if(word!=null)
        return lowerCaseWord=word.toLowerCase();
        else
        return null;
    }
    private bool validate(String word)
    {
        if(remainingWords.contains(word))
        {
            String lowerCaseWord = lowerCase(word);
            remainingWords.remove(lowerCaseWord);
            completedWords.add(lowerCaseWord);
            return true;
        }
        else
        return false;
    }


}