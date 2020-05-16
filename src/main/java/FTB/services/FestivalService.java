package FTB.services;


import FTB.model.Festival;
import FTB.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import FTB.exceptions.*;
import org.apache.commons.io.FileUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class FestivalService {

    private static List<Festival> festivals;
    public static final Path FESTIVALS_PATH = FestivalFileService.getPathToFile("config", "festival.json");

    public static void loadFestivalsFromFile() throws IOException {

        if (!Files.exists(FESTIVALS_PATH)) {
            FileUtils.copyURLToFile(FestivalService.class.getClassLoader().getResource("festivals.json"), FESTIVALS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        festivals = objectMapper.readValue(FESTIVALS_PATH.toFile(),
                new TypeReference<List<Festival>>() {
                });
}

    public static void addFestivals(String name, String details){

        festivals.add(new Festival(name,details));
        persistFestivals();
    }

    private static void persistFestivals() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(FESTIVALS_PATH.toFile(), festivals);
        } catch (IOException e) {
            throw new CouldNotWriteFestivalException();
        }
    }

}
