package com.AtlasLibrary;

public class Place{
    String place;
    public Place(String place){
        this.place = place;
    }
    public String getPlace(){
        return this.place;
    }
    public boolean equals(Place place){
        if(this.place!=null && place!=null){
            return this.place.equals(place.getPlace());
        }
        else{
            return (place==null || place.getPlace()==null) && this.getPlace()==null;
        }
    }
    public static boolean equals(Place place1, Place place2){
        if(place1!=null && place2!=null){
            return place1.equals(place2);
        }
        else{
            return place2==null && place1==null;
        }
    }
}