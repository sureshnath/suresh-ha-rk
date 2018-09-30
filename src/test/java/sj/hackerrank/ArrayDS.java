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

public class ArrayDS {

    @DataProvider(name = "dp")
    public Object[][] getData() {
        return getTestCases(getSrcTestResource("arrays-ds-testcases"));
    }

    @Test(dataProvider = "dp")
    public void test(String input, String expectedOutput) {
        StringWriter sw = new StringWriter();
        processSolution(getScanner(input), new PrintWriter(sw));
        Assert.assertEquals(trimmedAndLF(sw.toString()), trimmedAndLF(expectedOutput));
    }

    private static int[] reverseArray(int[] a) {
        int [] result = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = a[a.length-i-1];
        }
        return result;
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

        int[] res = reverseArray(arr);

        for (int i = 0; i < res.length; i++) {
            pw.print(String.valueOf(res[i]));

            if (i != res.length - 1) {
                pw.print(" ");
            }
        }
    }

}
