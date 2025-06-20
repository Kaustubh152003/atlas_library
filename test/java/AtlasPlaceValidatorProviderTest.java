package test.java;
import main.java.AtlasPlaceValidator;
import main.java.AtlasPlaceValidatorProvider;
import static test.java.TestRunner.AssertEquals;


class AtlasPlaceValidatorProviderTest {
    public static void runAllTests() throws TestRunner.AssertBooleansException {
        singletonProvisionTest();
    }

    public static boolean singletonProvisionTest() throws TestRunner.AssertBooleansException {
        AtlasPlaceValidator a = AtlasPlaceValidatorProvider.getAtlasPlaceValidator();
        AtlasPlaceValidator b = AtlasPlaceValidatorProvider.getAtlasPlaceValidator();
        AssertEquals(a==b, Boolean.TRUE);
        return true;
    }
}