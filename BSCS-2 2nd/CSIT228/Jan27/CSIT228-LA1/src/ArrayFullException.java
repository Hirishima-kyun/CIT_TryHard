public class ArrayFullException extends Exception {
    public ArrayFullException(int num) {
        super("The array is full and " + num + " cannot be inserted.");
    }
}
