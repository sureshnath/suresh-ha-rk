package sj.hackerrank;

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

public class MakingAnagrams {

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

    private static int makeAnagram(String a, String b) {
        int result = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (Character c : a.toCharArray()) {
            map.compute(c, (k, o) -> Optional.ofNullable(o).orElse(0) + 1 );
        }
        for (Character c : b.toCharArray()) {
            map.compute(c, (k, o) -> Optional.ofNullable(o).orElse(0) - 1 );
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            result += Math.abs(entry.getValue());
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
