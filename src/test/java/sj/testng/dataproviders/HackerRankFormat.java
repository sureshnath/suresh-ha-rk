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

    private static final String INPUT = "input";
    private static final String OUTPUT = "output";

    private static final String TESTCASE_PATTERN = ".*(\\d\\d)\\.txt$";
    private static final String TESTCASE_PATTERN_VAL = "$1";

    public static Object[][] getTestCases(Path folder) {
        final List<Object[]> list = new ArrayList<>();
        try {
            Files.list(folder.resolve(INPUT))
                    .filter(p->p.toString().matches(TESTCASE_PATTERN))
                    .forEach(inFile -> list.add(getTestCase(inFile.getParent().getParent()
                            , inFile.getFileName().toString().replaceAll(TESTCASE_PATTERN, TESTCASE_PATTERN_VAL))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Object[][] array = new Object[list.size()][];
        return list.toArray(array);
    }

    private static Object[] getTestCase(Path folder, String testcase) {
        return new Object[]{
                getFileContent(getFilePath(folder, INPUT, testcase))
                , getFileContent(getFilePath(folder, OUTPUT, testcase))
        };
    }

    private static Path getFilePath(Path folder, String type, String testcaseNum) {
        return folder.resolve(type).resolve(String.format("%s%s.txt", type, testcaseNum));
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

    public static Path getSrcTestResource(String folder) {
        return Paths.get("src/test/resources", folder);
    }
}
