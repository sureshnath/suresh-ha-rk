package sj.testng.dataproviders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HackerRankFormat {

    private static final Logger LOGGER = LoggerFactory.getLogger(HackerRankFormat.class);

    public static Object[][] getTestCases(Path folder) {
        final List<Object[]> list = new ArrayList<>();
        try {
            Files.newDirectoryStream(folder)
                    .forEach( p -> list.add( getTestCase(p) ) );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Object [][] array = new Object[list.size()][];
        return list.toArray(array);
    }

    private static Object[] getTestCase(Path folder) {
        return new Object[]{
                getFileContent(folder.resolve("input.txt")), getFileContent(folder.resolve("output.txt"))
        };
    }

    public static Scanner getScanner(String input) {
        return new Scanner(new StringReader(input));
    }

    private static String getFileContent(Path path) {
        try {
            LOGGER.info("Reading file content: {}", path);
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Path getSrcTestResource(String folder){
        return Paths.get("src/test/resources", folder);
    }
}
