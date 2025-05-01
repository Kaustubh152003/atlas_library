import java.util.Set;

public class AtlasPlaceValidator {
    private Set<String> validPlaces;
    public AtlasPlaceValidator() {
        initializeValidPlaces();
    }
    private void initializeValidPlaces(){
        
    }
    private String lowerCase(String word)
    {
        if(word!=null)
        return word.toLowerCase();
        else
        return null;
    }
    public boolean validate(String place)
    {
        return validPlaces.contains(lowerCase(place));
    }




}