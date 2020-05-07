package FTB.exceptions;

public class UserEmpty extends Exception{
    private String username;
    public UserEmpty(String username){
        super(String.format("Please enter an username!",username));

    }

}