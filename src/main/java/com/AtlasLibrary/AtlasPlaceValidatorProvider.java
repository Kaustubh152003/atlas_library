package com.AtlasLibrary;

public class AtlasPlaceValidatorProvider {
    private static final AtlasPlaceValidator atlasPlaceValidator = new AtlasPlaceValidator();
    private AtlasPlaceValidatorProvider() 
    {
        
    }
    public static AtlasPlaceValidator getAtlasPlaceValidator() {
        return atlasPlaceValidator;
    }
}