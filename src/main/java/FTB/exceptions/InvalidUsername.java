package FTB.exceptions;

public class InvalidUsername extends Exception {

    public InvalidUsername() {
        super(String.format("Your username is incorrect"));

    }
}