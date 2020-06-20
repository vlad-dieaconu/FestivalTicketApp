package FTB.controllers;

import FTB.model.Festival;
import FTB.services.FestivalFileService;
import FTB.services.FestivalService;
import FTB.services.FileSystemService;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;

import static org.junit.Assert.*;

public class AdminFestivalViewControllerTest  extends ApplicationTest {

    AdminFestivalViewController controller;
    FestivalService festivalService;
    public static final String TEST_NAME="testName";
    public static final String TEST_DETAILS="testDetails";


    @BeforeClass
    public static void setupClass() throws Exception {
        FestivalFileService.APPLICATION_FOLDER=".test-festival";
        FileSystemService.initApplicationHomeDirIfNeeded();

    }

    @Before
    public void setUp() throws IOException {
        FileUtils.cleanDirectory(FestivalFileService.getApplicationHomePath().toFile());

        controller = new AdminFestivalViewController();
        festivalService = new FestivalService();
        controller.Nume= new TextField();
        controller.Detalii = new TextArea();

        controller.Detalii.setText(TEST_DETAILS);
        controller.Nume.setText(TEST_NAME);

    }

    @Test
    public void handleAddFestAction() throws IOException {

        FestivalService.loadFestivalsFromFile();

        controller.handleAddFestAction();
        assertEquals(1,festivalService.festivals.size());



    }
}