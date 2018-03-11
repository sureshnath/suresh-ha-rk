package sj.hackerrank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static sj.testng.dataproviders.HackerRankFormat.*;

public class DequeTest {

    @DataProvider(name = "dp")
    public Object[][] getData() {
        return getTestCases( getSrcTestResource("java-dequeue-testcases"));
    }

    @Test(dataProvider = "dp")
    public void test(String input, String expectedOutput) {
        Assert.assertEquals(getSolution(getScanner(input)),expectedOutput.trim());
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(DequeTest.class);

    /**
     * @param map - map which caches a unique number and number of times it appears
     * @param key - unique number
     * @param isAdd - if unique number is to be added or subtracted
     * @return - number of unique number in the map
     */
    private static Integer numberOfUniq(Map<Integer,Integer> map, Integer key, boolean isAdd){
        LOGGER.debug("{} {} {} {}", isAdd?'A':'S', key, map.get(key));
        map.compute(key, (k,v) -> zeroToNull((isAdd? (v==null?1:v+1):v-1)) );
        LOGGER.debug("map size {}", map.size());
        return map.size();
    }

    private static Integer zeroToNull(Integer v){
        return v == 0 ? null : v;
    }

    private static String getSolution(Scanner in) {
        int n = in.nextInt();
        int m = in.nextInt();
        Deque<Integer> deque = new ArrayDeque<>();
        Map<Integer,Integer> map = new HashMap<>();
        Integer maxUniqCount = 0;
        for (int i = 0; i < n; i++) {
            int num = in.nextInt();
            deque.addLast(num);
            int uniqCount = numberOfUniq(map,num,true);
            if(i>m-1){
                int dropNum = deque.removeFirst();
                uniqCount = numberOfUniq(map,dropNum,false);
            }
            maxUniqCount = Math.max(maxUniqCount, uniqCount);
            LOGGER.debug("max {}", maxUniqCount);
        }
        return maxUniqCount.toString();
    }
}
