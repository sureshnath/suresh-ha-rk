package sj.hackerrank;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.BitSet;
import java.util.Scanner;

import static sj.testng.dataproviders.HackerRankFormat.*;

public class BitSetTest {

    @DataProvider(name = "dp")
    public Object[][] getData() {
        return getTestCases( getSrcTestResource("java_bitset_testcases"));
    }

    @Test(dataProvider = "dp")
    public void test(String input, String expectedOutput) {
        Assert.assertEquals(getBitSolution(getScanner(input)),expectedOutput);
    }

    private static String getBitSolution(Scanner in) {
        StringBuilder sb = new StringBuilder();
        int n = in.nextInt();
        int m = in.nextInt();
        BitSet b1 = new BitSet(n);
        BitSet b2 = new BitSet(n);
        for (int i = 0; i < m; i++) {
            String operation = in.next();
            int assignTo = in.nextInt();
            int indexOrBS = in.nextInt();
            BitSet left = assignTo == 1 ? b1 : b2;
            BitSet right = indexOrBS == 1 ? b1 : b2;
            switch (operation){
                case "AND": left.and(right); break;
                case "SET": left.set(indexOrBS); break;
                case "FLIP": left.flip(indexOrBS); break;
                case "OR": left.or(right); break;
                case "XOR": left.xor(right); break;
            }
            sb.append(b1.cardinality()).append(" ").append( b2.cardinality()).append("\n");
        }
        return sb.toString();
    }
}
