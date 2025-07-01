
import com.AtlasLibrary.AtlasPlaceValidator;
import com.AtlasLibrary.AtlasPlaceValidatorProvider;



class AtlasPlaceValidatorProviderTest {
    public static void runAllTests() throws TestRunner.AssertBooleansException {
        singletonProvisionTest();
    }

    public static boolean singletonProvisionTest() throws TestRunner.AssertBooleansException {
        AtlasPlaceValidator a = AtlasPlaceValidatorProvider.getAtlasPlaceValidator();
        AtlasPlaceValidator b = AtlasPlaceValidatorProvider.getAtlasPlaceValidator();
        TestRunner.AssertEquals(a==b, Boolean.TRUE);
        return true;
    }
}