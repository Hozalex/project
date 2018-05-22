package HomeWork2.MyException;

public class MyArrayDataException extends Exception {

    String message;

    public MyArrayDataException(String message) {
        super(message);
        this.message = message;
    }
}
