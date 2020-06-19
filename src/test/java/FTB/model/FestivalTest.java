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

import static org.junit.Assert.*;

public class FestivalTest {

    private static final String FESTIVAL_NAME = "testName";
    private static final String FESTIVAL_DETAILS = "testDetails";
    private Festival festival;

    @Before
    public void setUp() throws Exception {

        festival=new Festival();
        festival.setFestivalDetails(FESTIVAL_DETAILS);
        festival.setFestivalName(FESTIVAL_NAME);
    }

    @Test
    public void testGetFestivalName() {

        String s="";
        s=festival.getFestivalName();
        assertEquals(s,FESTIVAL_NAME);
    }

    @Test
    public void testGetFestivalDetails(){
        String s="";
        s=festival.getFestivalDetails();
        assertEquals(s,FESTIVAL_DETAILS);
    }
}