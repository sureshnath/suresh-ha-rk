package sj.hackerrank;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.StringTokenizer;

import static sj.testng.dataproviders.HackerRankFormat.*;
import static sj.testng.dataproviders.HackerRankFormat.trimmedAndLF;

interface PerformOperation {
    boolean check(int a);
}
class MyMath {
    public static boolean checker(PerformOperation p, int num) {
        return p.check(num);
    }

    PerformOperation isOdd(){
        return i -> i % 2 == 1;
    }

    PerformOperation isPrime(){
        return i -> new BigInteger(""+i).isProbablePrime(100);
    }

    PerformOperation isPalindrome(){
        return i -> (""+i).equals(new StringBuilder(""+i).reverse().toString());
    }
}

public class LamdaTest {
    @DataProvider(name = "dp")
    public Object[][] getData() {
        return getTestCases(getSrcTestResource("java-lambda-expressions-testcases"));
    }

    @Test(dataProvider = "dp")
    public void test(String input, String expectedOutput) {
        StringWriter sw = new StringWriter();
        processSolution(getScanner(input), new PrintWriter(sw));
        Assert.assertEquals(trimmedAndLF(sw.toString()), trimmedAndLF(expectedOutput));
    }

    private void processSolution(Scanner scan, PrintWriter pw) {
        MyMath ob = new MyMath();
        int T = Integer.parseInt(scan.nextLine());
        PerformOperation op;
        boolean ret = false;
        String ans = null;
        while (T-- > 0) {
            String s = scan.nextLine();
            StringTokenizer st = new StringTokenizer(s);
            int ch = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            if (ch == 1) {
                op = ob.isOdd();
                ret = ob.checker(op, num);
                ans = (ret) ? "ODD" : "EVEN";
            } else if (ch == 2) {
                op = ob.isPrime();
                ret = ob.checker(op, num);
                ans = (ret) ? "PRIME" : "COMPOSITE";
            } else if (ch == 3) {
                op = ob.isPalindrome();
                ret = ob.checker(op, num);
                ans = (ret) ? "PALINDROME" : "NOT PALINDROME";

            }
            pw.println(ans);
        }
    }
}