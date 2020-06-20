package FTB.controllers;

import static org.junit.Assert.*;

import FTB.Controller;
import FTB.exceptions.EmptyPassword;
import FTB.exceptions.UsernameAlreadyExistsException;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import FTB.services.FileSystemService;
import FTB.services.UserService;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;

public class RegisterControllerTest extends ApplicationTest {

    public static final String TEST_USER = "testUser";
    public static final String TEST_PASSWORD = "testPassword";
    public static final String TEST_EMAIL="testEmail";
    public static final String TEST_AGE="age";

    private RegisterController controller;

    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration-example";
        FileSystemService.initApplicationHomeDirIfNeeded();
        UserService.loadUsersFromFile();
    }

    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        UserService.loadUsersFromFile();

        controller = new RegisterController();
        controller.usernameField = new TextField();
        controller.passwordField = new PasswordField();
        controller.registrationMessage = new Text();
        controller.email= new TextField();
        controller.age =new TextField();
        controller.age=new TextField();

        controller.passwordField.setText(TEST_PASSWORD);
        controller.usernameField.setText(TEST_USER);
        controller.age.setText(TEST_AGE);
        controller.email.setText(TEST_EMAIL);
    }

    @Test
    public void testHandleAddUserActionCode() {
        controller.handleRegisterAction();
        assertEquals(1, UserService.getUsers().size());
        assertEquals("Account created successfully!", controller.registrationMessage.getText());
    }

    @Test
    public void testAddSameUserTwice() {
        controller.handleRegisterAction();
        controller.handleRegisterAction();
        assertEquals("An account with the username " + TEST_USER + " already exists!", controller.registrationMessage.getText());
    }



}