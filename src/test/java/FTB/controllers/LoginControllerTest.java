package FTB.controllers;

import FTB.exceptions.EmptyPassword;
import FTB.exceptions.InvalidPassword;
import FTB.exceptions.InvalidUsername;
import FTB.exceptions.UserEmpty;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import FTB.controllers.RegisterController;
import FTB.services.FileSystemService;
import FTB.services.UserService;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;





public class LoginControllerTest extends ApplicationTest {
    public static final String TEST_USER = "testUser";
    public static  String TEST_PASSWORD = "testPassword";
    public static String TEST_EMAIL= "testEmail";
    public static String TEST_AGE = "testAge";
    private LoginController controller;
    private RegisterController regController;

    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration-example";
        FileSystemService.initApplicationHomeDirIfNeeded();
    }
    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        UserService.loadUsersFromFile();
        controller=new LoginController();
        regController=new RegisterController();
        controller.user_input=new TextField();
        controller.loginMessage=new Text();
        controller.passwordField=new PasswordField();
        regController.age=new TextField();
        regController.email=new TextField();
        controller.passwordField.setText(TEST_PASSWORD);
        controller.user_input.setText(TEST_USER);

        regController.registrationMessage=new Text();
        regController.passwordField = new PasswordField();
        regController.usernameField=new TextField();
        regController.passwordField.setText(TEST_PASSWORD);
        regController.usernameField.setText(TEST_USER);
        regController.age.setText(TEST_AGE);
        regController.email.setText(TEST_EMAIL);
    }
    @Test
    public void NoUsername() throws InvalidUsername, InvalidPassword, EmptyPassword, UserEmpty, IOException {
        regController.handleRegisterAction();
        controller.passwordField.setText("");
        controller.user_input.setText("");
        controller.handleLoginButtonAction(new ActionEvent());
        assertEquals("Please enter an username!",controller.loginMessage.getText());
    }

    @Test
    public void NoPassword() throws InvalidUsername, InvalidPassword, EmptyPassword, UserEmpty, IOException {
        regController.handleRegisterAction();
        controller.passwordField.setText("");
        controller.handleLoginButtonAction(new ActionEvent());
        assertEquals("Please enter a password!",controller.loginMessage.getText());
    }

    @Test
    public void InvalidPassword() throws InvalidUsername, InvalidPassword, EmptyPassword, UserEmpty, IOException {
        regController.handleRegisterAction();
        controller.passwordField.setText("ceva");
        controller.handleLoginButtonAction(new ActionEvent());
        assertEquals("Your password is wrong!",controller.loginMessage.getText());
    }

    @Test
    public void InvalidUsername() throws InvalidUsername, InvalidPassword, EmptyPassword, UserEmpty, IOException {
        regController.handleRegisterAction();
        controller.user_input.setText("ceva");
        controller.handleLoginButtonAction(new ActionEvent());
        assertEquals("Your username is incorrect",controller.loginMessage.getText());
    }


}