package sj.hackerrank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

import static sj.testng.dataproviders.HackerRankFormat.*;

public class PrioritiesTest {
    private final static Priorities priorities = new Priorities();

    static class Priorities{
        private static Logger LOGGER = LoggerFactory.getLogger(Priorities.class);

        PriorityQueue<Student> queue = new PriorityQueue<>();
        List<Student> getStudents(List<String> events){
            for (String eventDetail:
                 events) {
                Scanner scanner = new Scanner(eventDetail);
                String event = scanner.next();
                if (event.startsWith("ENTER")){
                    String name = scanner.next();
                    double cgpa = scanner.nextDouble();
                    int id = scanner.nextInt();
                    queue.add(new Student(id, name, cgpa));
                }else if (event.startsWith("SERVED") && queue.size()>0){
                    Student out = queue.remove();
                    LOGGER.debug("{}", out.name);
                }
            }
            List<Student> list = new ArrayList<>(queue);
            Collections.sort(list);
            return list;
        }
    }

    static class Student implements Comparable<Student>{

        private static Logger LOGGER = LoggerFactory.getLogger(Student.class);
        Integer id;
        String name;
        Double cgpa;

        Student(int id, String name, double cgpa) {
            this.id = id;
            this.name = name;
            this.cgpa = cgpa;
        }

        Integer getID() {
            return id;
        }

        String getName() {
            return name;
        }

        Double getCGPA() {
            return cgpa;
        }

        @Override
        public int compareTo(Student o) {
            int retValue = o.getCGPA().compareTo(getCGPA());
            if (retValue == 0){
                retValue = getName().compareTo(o.getName());
                if (retValue == 0){
                    retValue = getID().compareTo(o.getID());
                }
            }
            LOGGER.debug("{} ({} . {}) {}", this, retValue, (retValue==0?'=':(retValue<0?'>':'<')), o);
            return retValue;
        }

        @Override
        public String toString() {
            return String.format("%s %s %s", getCGPA(), getName(), getID());
        }
    }

    @DataProvider(name = "dp")
    public Object[][] getData() {
        return getTestCases(getSrcTestResource("java-priority-queue-testcases"));
    }

    @Test(dataProvider = "dp")
    public void test(String input, String expectedOutput) {
        StringWriter sw = new StringWriter();
        processSolution(getScanner(input), new PrintWriter(sw));
        Assert.assertEquals( trimmedAndLF(sw.toString()), trimmedAndLF(expectedOutput));
    }

    private void processSolution(Scanner scan, PrintWriter pw) {
        int totalEvents = Integer.parseInt(scan.nextLine());
        List<String> events = new ArrayList<>();

        while (totalEvents-- != 0) {
            String event = scan.nextLine();
            events.add(event);
        }

        List<Student> students = priorities.getStudents(events);

        if (students.isEmpty()) {
            pw.println("EMPTY");
        } else {
            for (Student st : students) {
                pw.println(st.getName());
            }
        }
    }
}
