package sj.hackerrank;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static sj.testng.dataproviders.HackerRankFormat.*;

public class DequeTest {

    @DataProvider(name = "dp")
    public Object[][] getData() {
        return getTestCases(getSrcTestResource("java-dequeue-testcases"));
    }

    @Test(dataProvider = "dp")
    public void test(String input, String expectedOutput) {
        Assert.assertEquals(getSolution(getScanner(input)), expectedOutput.trim());
    }

    private static String getSolution(Scanner in) {
        int n = in.nextInt();
        int m = in.nextInt();
        Deque<Integer> deque = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();
        Integer maxUniqCount = 0;
        for (int i = 0; i < n; i++) {
            int key = in.nextInt();
            deque.addLast(key);
            map.compute(key, (k, v) -> (v==null?0:v)+1);
            int uniqCount = map.size();
            if (i > m - 1) {
                key = deque.removeFirst();
                map.compute(key, (k, v) -> v-1==0?null:v-1);
                uniqCount = map.size();
            }
            maxUniqCount = Math.max(maxUniqCount, uniqCount);
        }
        return maxUniqCount.toString();
    }
}
