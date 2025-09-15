package com.AtlasLibrary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class AtlasBot extends GamePlayer {
    private Map<Character,ArrayList<String>> FirstLetterToKnownPlacesMap;
    public AtlasBot(int playerId,String playerName,String personalKey) throws IOException {
        super(playerId,playerName,personalKey);
        initializeKnownPlaces();
    }
    public AtlasBot(int playerId,String personalKey) throws IOException {
        super(playerId,personalKey);
        initializeKnownPlaces();
    }
    public AtlasBot(GamePlayer player) throws IOException{
        super(player);
        initializeKnownPlaces();
    }
    public boolean isBot(){
        return true;
    }
    private void initializeKnownPlaces()  throws IOException{
        String filePath="src/main/resources/ValidPlaces.txt";
        initializeKnownPlaces(filePath);
    }
    public boolean addWordInVocabulary(String place)
    {
        String lowerCasePlace = lowerCase(place).trim();
        Character firstLetter = lowerCasePlace.charAt(0);
        ArrayList<String> knownPlacesForLetter = FirstLetterToKnownPlacesMap.get(firstLetter);
        if(knownPlacesForLetter==null)
        {
            knownPlacesForLetter = new ArrayList<>();
            FirstLetterToKnownPlacesMap.put(firstLetter,knownPlacesForLetter);
        }
        knownPlacesForLetter.add(place);
        return true;
    }
    private void initializeKnownPlaces(String filePath) throws IOException{
        this.FirstLetterToKnownPlacesMap=new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String place;
            while ((place = reader.readLine()) != null) {
                addWordInVocabulary(place);
            }
        }

    }
    private String lowerCase(String word)
    {
        if(word!=null)
        return word.toLowerCase();
        else
        return null;
    }
    public ActionResponse playPlace(char startingLetter, AtlasOrchestrator atlasOrchestrator){
        ArrayList<String> placesStartingWithLetter = FirstLetterToKnownPlacesMap.get(startingLetter);
        int size=placesStartingWithLetter.size();
        int j=0;
        while(placesStartingWithLetter!=null && j<size && atlasOrchestrator.isWordCompleted(placesStartingWithLetter.get(j))==true)
        {
            j++;
        }
        if(j==size || placesStartingWithLetter==null)
        {
            return atlasOrchestrator.skipTurn(this);

        }
        else{
            return atlasOrchestrator.play(this, placesStartingWithLetter.get(j));
        }

    }
    public ActionResponse skipTurn(AtlasOrchestrator atlasOrchestrator){
        return atlasOrchestrator.skipTurn(this);
    }
    public ActionResponse playTurn(AtlasOrchestrator atlasOrchestrator){
        if(atlasOrchestrator.getTurn().equals(this)){
            char startingLetter = atlasOrchestrator.getCurrentLetter();
            return playPlace(startingLetter,atlasOrchestrator);
        }
        else{
            return ActionResponse.getFailedActionResponse("Not your turn");
        }
    }
    
}