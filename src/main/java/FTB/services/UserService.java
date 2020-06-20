package FTB.services;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import FTB.exceptions.*;
import org.apache.commons.io.FileUtils;
import FTB.model.User;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;


public class UserService {

    static List<User> users;
    public static  Path USERS_PATH = FileSystemService.getPathToFile("config", "users.json");



    public static void loadUsersFromFile() throws IOException {

        if (!Files.exists(USERS_PATH)) {
            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("users.json"), USERS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        users = objectMapper.readValue(USERS_PATH.toFile(),
                new TypeReference<List<User>>() {
                });
    }

    public static void addUser(String username, String password, String email, String age) throws UsernameAlreadyExistsException,EmptyPassword,UserEmpty {
        checkUserDoesNotAlreadyExist(username);
        checkUserIsNotEmpty(username);
        checkPassIsNotEmpty(password);
        users.add(new User(username, encodePassword(username, password), "client",email,age));
        persistUsers();
    }

    public static void checkUser(String username,String password) throws EmptyPassword,UserEmpty, InvalidPassword,InvalidUsername{
        checkUserIsNotEmpty(username);
        checkPassIsNotEmpty(password);
        checkUsername(username,password);
    }

    private static void checkUsername(String username, String password) throws InvalidPassword,InvalidUsername{
        int ok=0;
        for (User user : users) {
            if (Objects.equals(username, user.getUsername()))
            {ok=1;

                if(Objects.equals(encodePassword(username,password), user.getPassword()))
                    ok=2;
            }
        }
        if(ok==0)
            throw new InvalidUsername();
        if(ok==1)
            throw new InvalidPassword();


    }

    static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException{
        for (User user : users) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }
    static void checkUserIsNotEmpty(String username)throws UserEmpty {
        if(Objects.equals(username, ""))
            throw new UserEmpty(username);
    }

    static void checkPassIsNotEmpty(String password)throws EmptyPassword {
        if(Objects.equals(password,""))
            throw new EmptyPassword(password);
    }

    private static void persistUsers() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), users);
        } catch (IOException e) {
            throw new CouldNotWriteUsersException();
        }
    }

    static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }

    public static List<User> getUsers(){
        return users;
    }

}