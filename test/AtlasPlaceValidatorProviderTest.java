package test;
import main.AtlasPlaceValidator;
import main.AtlasPlaceValidatorProvider;
import static test.TestRunner.AssertEquals;


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