package HomeWork2.MyException;

public class MyArraySizeException extends Exception {

    String message;

    public MyArraySizeException(String message) {
        super(message);
        this.message = message;
    }
}
