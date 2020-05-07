package FTB.exceptions;

public class InvalidPassword extends Exception {

    public InvalidPassword() {
        super(String.format("Your password is wrong!"));

    }
}