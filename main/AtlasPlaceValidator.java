package main;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class AtlasPlaceValidator {
    private Set<String> validPlaces;
    public AtlasPlaceValidator() {
        initializeValidPlaces();
    }
    public AtlasPlaceValidator(String filePath) {
        initializeValidPlaces(filePath);
    }
    private void initializeValidPlaces(){
        String filePath="main/resources/ValidPlaces.txt";
        initializeValidPlaces(filePath);
    }
    private void initializeValidPlaces(String filePath){
        this.validPlaces=new TreeSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) 
        {
            String Place;
            while ((Place = reader.readLine()) != null) {
                validPlaces.add(lowerCase(Place.trim()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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