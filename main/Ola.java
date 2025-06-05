package main;
public class Ola {
    public static void main(String[] args) {
        AtlasPlaceValidator a= new AtlasPlaceValidator();
        String place = "Delhi";
        System.out.print(a.validate(place));
    }
}