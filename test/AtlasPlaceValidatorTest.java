package test;
import java.util.Arrays;
import java.util.List;
import main.AtlasPlaceValidator;
import static test.TestRunner.AssertEquals;

class AtlasPlaceValidatorTest {
    public static void runAllTests() throws Exception{
        validateTrueTest();
        validateFalseTest();
    }
    public static Boolean validateTrueTest() throws Exception {
        AtlasPlaceValidator atlasPlaceValidator= new AtlasPlaceValidator();
        List<String> validSamplePlaces = Arrays.asList("Buenos Aires", "Córdoba", "La PlaTa", "KhamMam");
        int j=0;
        while(j<validSamplePlaces.size())
        {
            String place = validSamplePlaces.get(j);
            AssertEquals(atlasPlaceValidator.validate(place),Boolean.TRUE);
            j++;
        }
        return true;
    }
    public static Boolean validateFalseTest() throws Exception {
        AtlasPlaceValidator atlasPlaceValidator= new AtlasPlaceValidator();
        List<String> invalidSamplePlaces = Arrays.asList("NoPlace", "AgainNotAPlace");
        int j=0;
        while(j<invalidSamplePlaces.size())
        {
            String place = invalidSamplePlaces.get(j);
            AssertEquals(atlasPlaceValidator.validate(place),Boolean.FALSE);
            j++;
        }
        return true;
    }

}