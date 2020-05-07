package FTB.exceptions;

public class EmptyPassword extends Exception{
    private String password;
    public EmptyPassword(String password){
        super(String.format("Please enter a password!", password));
    }
}