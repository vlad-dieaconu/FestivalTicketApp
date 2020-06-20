package FTB.model;

import FTB.controllers.RegisterController;
import FTB.services.FileSystemService;
import FTB.services.UserService;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class UserTest {

    public static final String TEST_USERNAME = "testGetUsername";
    public static final String TEST_PASSWORD = "testGetPassword";
    public static final String TEST_ROLE = "testGetRole";
    public static final double TEST_ACCBALLANCE = 1000.0;
    public static final String TEST_EMAIL = "testGetEmail";
    public static final String TEST_AGE = "testGetAge";
    public static final ArrayList<String> TEST_ORDERS = new ArrayList<String>();
    public static final ArrayList<Integer> TEST_ORDERS_APPROVED = new ArrayList<Integer>();
    public static final int TEST_CURRENT_ORDER = 1;

    private User user;

    @Before
    public void setUp() throws Exception {

        user = new User();
        user.setUsername(TEST_USERNAME);
        user.setPassword(TEST_PASSWORD);
        user.setRole(TEST_ROLE);
        user.setAccBalance(TEST_ACCBALLANCE);
        user.setEmail(TEST_EMAIL);
        user.setAge(TEST_AGE);
        TEST_ORDERS.add("testGetOrders");
        TEST_ORDERS.add("order1");
        user.setOrders(TEST_ORDERS);
        TEST_ORDERS_APPROVED.add(0);
        user.setOrdersApproved(TEST_ORDERS_APPROVED);

    }

    @Test
    public void testGetUsername(){
        String s = " ";
        s=user.getUsername();
        assertEquals(s,TEST_USERNAME);
    }

    @Test
    public void testGetPassword(){
        String s = " ";
        s=user.getPassword();
        assertEquals(s,TEST_PASSWORD);
    }

    @Test
    public void testGetRole(){
        String s = " ";
        s=user.getRole();
        assertEquals(s,TEST_ROLE);
    }

    @Test
    public void testGetAccBallance(){
        double d = 0;
        d=user.getAccBalance();
        assertEquals(d,TEST_ACCBALLANCE,0);
    }

    @Test
    public void testGetEmail(){
        String s = " ";
        s=user.getEmail();
        assertEquals(s,TEST_EMAIL);
    }

    @Test
    public void testGetAge(){
        String s = " ";
        s=user.getAge();
        assertEquals(s,TEST_AGE);
    }

    @Test
    public void testGetOrders(){
        ArrayList<String> s = new ArrayList<String>();
        s=user.getOrders();
        assertEquals(s,TEST_ORDERS);
    }

    @Test
    public void testGetOrdersApproved(){
        ArrayList<Integer> i = new ArrayList<Integer>();
        i=user.getOrdersApproved();
        assertEquals(i,TEST_ORDERS_APPROVED);
    }

    @Test
    public void testGetCurrentOrder() {
        String s = "";
        s=user.getCurrentOrder(TEST_CURRENT_ORDER);
        assertEquals(s," order1");

    }
}