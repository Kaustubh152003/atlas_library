public class AtlasPlaceValidatorProvider {
    private static AtlasPlaceValidator atlasPlaceValidator = new AtlasPlaceValidator();
    private AtlasPlaceValidatorProvider() 
    {

    }
    public static AtlasPlaceValidator getAtlasPlaceValidator() {
        return atlasPlaceValidator;
    }
}