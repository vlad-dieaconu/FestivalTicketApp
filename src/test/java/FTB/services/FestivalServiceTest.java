package FTB.services;

import FTB.model.Festival;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class FestivalServiceTest {


    @BeforeClass
    public static void setupClass() {
        FestivalFileService.APPLICATION_FOLDER = ".test-festival";
        FestivalFileService.initApplicationHomeDirIfNeeded();
    }

    @Before
    public void setUp() throws IOException {
        FileUtils.cleanDirectory(FestivalFileService.getApplicationHomePath().toFile());
    }

    @Test
    public void testCopyDefaultFileIfNotExists() throws Exception {
        FestivalService.loadFestivalsFromFile();
        assertTrue(Files.exists(FestivalService.FESTIVALS_PATH));
    }

    @Test
    public void testFestivalsFromFile() throws Exception {
        FestivalService.loadFestivalsFromFile();
        assertNotNull(FestivalService.festivals);
        assertEquals(0, FestivalService.festivals.size());
    }

    @Test
    public void testLoadFestivalsFromFile() throws Exception {
        FestivalService.loadFestivalsFromFile();
        assertNotNull(FestivalService.festivals);
        assertEquals(0, FestivalService.festivals.size());
    }

    @Test
    public void testAddFestival() throws Exception {
        FestivalService.loadFestivalsFromFile();
        FestivalService.addFestivals("test0","DETAILS0");
        assertNotNull(FestivalService.festivals);
        assertEquals(1, FestivalService.festivals.size());
    }

}