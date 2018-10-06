package sj.hackerrank;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

import static sj.testng.dataproviders.HackerRankFormat.*;

public class ArrayFindDuplicates {

    @DataProvider(name = "dp")
    public Object[][] getData() {
        return getTestCases(getSrcTestResource("arrays-find-duplicates-testcases"));
    }

    @Test(dataProvider = "dp")
    public void test(String input, String expectedOutput) {
        StringWriter sw = new StringWriter();
        processSolution(getScanner(input), new PrintWriter(sw));
        Assert.assertEquals(trimmedAndLF(sw.toString()), trimmedAndLF(expectedOutput));
    }

    private static int[] findDuplicates(int[] a) {
        Map<Integer,Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < a.length; i++) {
            map.compute(a[i],(k,o)->Optional.ofNullable(o).orElse(0)+1);
        }
        return map.entrySet().stream().filter(e->e.getValue()>1).mapToInt(Map.Entry::getKey).toArray();
    }

    private void processSolution(Scanner scanner, PrintWriter pw) {
        int arrCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[arrCount];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < arrCount; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int[] res = findDuplicates(arr);

        for (int i = 0; i < res.length; i++) {
            pw.print(String.valueOf(res[i]));

            if (i != res.length - 1) {
                pw.print(" ");
            }
        }
    }

}
