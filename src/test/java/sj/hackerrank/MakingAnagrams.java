package sj.hackerrank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import static sj.testng.dataproviders.HackerRankFormat.*;
import static sj.testng.dataproviders.HackerRankFormat.trimmedAndLF;

public class MakingAnagrams {

    private static Logger LOGGER = LoggerFactory.getLogger(MakingAnagrams.class);

    @DataProvider(name = "dp")
    public Object[][] getData() {
        return getTestCases(getSrcTestResource("java-making-anagrams-testcases"));
    }

    @Test(dataProvider = "dp")
    public void test(String input, String expectedOutput) {
        StringWriter sw = new StringWriter();
        processSolution(getScanner(input), new PrintWriter(sw));
        Assert.assertEquals(trimmedAndLF(sw.toString()), trimmedAndLF(expectedOutput));
    }

    static class BiCount {
        int aCount;
        int bCount;

        static BiCount getOrDefault(BiCount old) {
            return Optional.ofNullable(old).orElse(new BiCount());
        }

        BiCount addA() {
            aCount++;
            return this;
        }

        BiCount addB() {
            bCount++;
            return this;
        }

        int diff() {
            return Math.max(aCount, bCount) - Math.min(aCount, bCount);
        }
    }

    private static int makeAnagram(String a, String b) {
        int result = 0;
        Map<Character, BiCount> map = new HashMap<>();
        for (Character c : a.toCharArray()) {
            map.compute(c, (k, o) -> BiCount.getOrDefault(o).addA());
        }
        for (Character c : b.toCharArray()) {
            map.compute(c, (k, o) -> BiCount.getOrDefault(o).addB());
        }
        for (Map.Entry<Character, BiCount> entry : map.entrySet()) {
            result += entry.getValue().diff();
        }
        return result;
    }

    private void processSolution(Scanner scanner, PrintWriter pw) {
        String a = scanner.nextLine();
        String b = scanner.nextLine();
        int res = makeAnagram(a, b);
        pw.println(res);
        scanner.close();
    }

}
