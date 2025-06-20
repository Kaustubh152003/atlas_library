package main;
public class AtlasExceptions {
    
    public static class AtlasGameException extends Exception {
        public AtlasGameException(String message) {
            super(message);
        }
    }
    public static class AtlasPlayException extends AtlasGameException {
        public AtlasPlayException(String message){
            super(message);
        }
    }
    public static class AtlasPlayerDoesNotExistException extends AtlasGameException {
        public AtlasPlayerDoesNotExistException(String message) {
            super(message);
        }
    }

}