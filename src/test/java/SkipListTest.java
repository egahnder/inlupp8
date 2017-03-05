import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class SkipListTest {
    SkipList<String> classUnderTest;

    @Before
    public void setUp(){
        classUnderTest = new SkipList<>();
    }

    @Test public void oneNode() {

        classUnderTest.add("Daniel");

        assertEquals("Daniel", classUnderTest.getElement("Daniel"));
    }

    @Test
    public void addDuplicates() {

        classUnderTest.add("Tobias");
        classUnderTest.add("Daniel");
        classUnderTest.add("Daniel");
        classUnderTest.add("Eric");

        assertEquals("Daniel", classUnderTest.getElement("Daniel"));
    }

    @Test
    public void removeOneDuplicate() {

        classUnderTest.add("Tobias");
        classUnderTest.add("Daniel");
        classUnderTest.add("Daniel");
        classUnderTest.add("Eric");

        assertEquals("Daniel", classUnderTest.getElement("Daniel"));
        classUnderTest.remove("Daniel");
        assertEquals("Daniel", classUnderTest.getElement("Daniel"));
        classUnderTest.remove("Daniel");
        assertEquals(null, classUnderTest.getElement("Daniel"));
    }

    @Test
    public void removeLastElement() {

        classUnderTest.add("Tobias");
        classUnderTest.add("Daniel");

        classUnderTest.remove("Daniel");
        classUnderTest.remove("Tobias");
        assertEquals(null, classUnderTest.getElement("Daniel"));
        assertEquals(null, classUnderTest.getElement("Tobias"));
        assertEquals(null, classUnderTest.getElement("Something"));
    }

    @Test
    public void removeNonExistentElement() {

        classUnderTest.add("Tobias");
        classUnderTest.add("Daniel");

        assertEquals(null, classUnderTest.remove("Danne"));
    }

    @Test
    public void removeAllAndAddNew() {

        classUnderTest.add("Tobias");
        classUnderTest.add("Daniel");

        classUnderTest.remove("Tobias");
        classUnderTest.remove("Daniel");

        classUnderTest.add("Eric");
        classUnderTest.add("Tobias");

        assertEquals("Eric", classUnderTest.getElement("Eric"));
        assertEquals("Tobias", classUnderTest.getElement("Tobias"));

    }

    @Test
    public void bigTest(){
        Random rand = new Random();
        ArrayList<String> oracle = new ArrayList<>();
        for (int i = 0; i < 100000; i++){
            oracle.add(Integer.toString(i));
            classUnderTest.add(Integer.toString(i));
        }

        for (int i = 0; i < 100000; i++){
            String s = oracle.remove(rand.nextInt(oracle.size()));
            assertEquals(s, classUnderTest.getElement(s));
            assertEquals(s, classUnderTest.remove(s));
            assertEquals(null, classUnderTest.getElement(s));
        }
    }


//    @Test
//    public void speedTest(){
//        int n = 1000000;
//        Random rand = new Random();
//        for (int i = 0; i < n; i++){
//            int j = rand.nextInt(n);
//            classUnderTest.add(Integer.toString(j));
//        }
//
//        for (int i = 0; i < n; i++){
//            int j = rand.nextInt(n);
//            classUnderTest.getElement(Integer.toString(n));
//        }
//        for (int i = 0; i < n; i++){
//            int j = rand.nextInt(n);
//            classUnderTest.remove(Integer.toString(n));
//        }
//    }
}
