public class InvalidPositionException extends RuntimeException {
    public InvalidPositionException(int size) {
        super("Position must be between 1 and " + size);
    }
}
