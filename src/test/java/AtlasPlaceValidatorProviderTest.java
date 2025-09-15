
import com.AtlasLibrary.AtlasPlaceValidator;
import com.AtlasLibrary.AtlasPlaceValidatorProvider;



class AtlasPlaceValidatorProviderTest {
    public static void runAllTests() throws TestRunner.AssertException {
        singletonProvisionTest();
    }

    public static boolean singletonProvisionTest() throws TestRunner.AssertException {
        AtlasPlaceValidator a = AtlasPlaceValidatorProvider.getAtlasPlaceValidator();
        AtlasPlaceValidator b = AtlasPlaceValidatorProvider.getAtlasPlaceValidator();
        TestRunner.AssertEquals(a==b, Boolean.TRUE);
        return true;
    }
}