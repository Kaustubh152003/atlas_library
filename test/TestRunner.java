package test;
public class TestRunner {
    public static class AssertBooleansException extends Exception {
        public AssertBooleansException(String message,Object actual, Object expected) {
            super(message);
            System.err.println("Actual : " + actual);
            System.err.println("Expected : " + expected);
        }
    }
    public static Boolean AssertEquals(Object actual, Object expected) throws AssertBooleansException
    {
        if(actual.equals(expected)){
            return true;
        }
        else
        {
            throw new AssertBooleansException("Assertion Failed",actual,expected);
        }
    }
    public static void AllTestsPassed(){
        System.out.println("All Tests have Passed\n");
    }
    public static void main(String[] args) throws Exception {
        AtlasPlaceValidatorTest.runAllTests();
        AtlasPlaceValidatorProviderTest.runAllTests();
        AtlasGameHistoryTest.runAllTests();

        AllTestsPassed();

    }
}