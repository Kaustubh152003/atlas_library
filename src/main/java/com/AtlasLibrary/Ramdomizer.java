package com.AtlasLibrary;

public class Ramdomizer {
    public static long getRandomNumber(){
        double randomFraction =  Math.random();
        long randomNumber = (long)Math.floor(randomFraction*Math.pow(10,17));
        return randomNumber;
    }
    public static long getRandomNumberInRange(long low,long high){
        long randomNumber=getRandomNumber();
        long range = high-low+1;
        long randomNumberInRange = randomNumber%range + low;
        return randomNumberInRange;
    }
    public static Character getRandomLetter(){
        long randomNumber=getRandomNumberInRange(0, 25);
        Character randomLetter = (char)(randomNumber + (int)'a'); 
        return randomLetter;
    }
}
