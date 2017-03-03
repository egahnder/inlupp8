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

    @Test public void twoNodesDaniel() {

        classUnderTest.add("Daniel");
        classUnderTest.add("Tobias");

        assertEquals("Daniel", classUnderTest.getElement("Daniel"));
    }

    @Test public void twoNodesTobias() {

        classUnderTest.add("Daniel");
        classUnderTest.add("Tobias");

        assertEquals("Tobias", classUnderTest.getElement("Tobias"));
    }

    @Test public void threeNodesTobias() {

        classUnderTest.add("Daniel");
        classUnderTest.add("Tobias");
        classUnderTest.add("Eric");

        assertEquals("Tobias", classUnderTest.getElement("Tobias"));
    }

    @Test public void threeNodesEric() {

        classUnderTest.add("Daniel");
        classUnderTest.add("Tobias");
        classUnderTest.add("Eric");

        assertEquals("Eric", classUnderTest.getElement("Eric"));
    }

    @Test public void fourNodesDaniel() {

        classUnderTest.add("Daniel");
        classUnderTest.add("Tobias");
        classUnderTest.add("Eric");
        classUnderTest.add("Anton");

        assertEquals("Daniel", classUnderTest.getElement("Daniel"));
    }

    @Test public void nineNodes() {

        classUnderTest.add("Daniel");
        classUnderTest.add("Tobias");
        classUnderTest.add("Eric");
        classUnderTest.add("Daniel1");
        classUnderTest.add("Tobias1");
        classUnderTest.add("Eric1");
        classUnderTest.add("Daniel2");
        classUnderTest.add("Tobias2");
        classUnderTest.add("Eric2");

        assertEquals("Tobias2", classUnderTest.getElement("Tobias2"));
    }

    @Test public void nineNodesNineGetElement() {

        classUnderTest.add("Daniel");
        classUnderTest.add("Tobias");
        classUnderTest.add("Eric");
        classUnderTest.add("Daniel1");
        classUnderTest.add("Tobias1");
        classUnderTest.add("Eric1");
        classUnderTest.add("Daniel2");
        classUnderTest.add("Tobias2");
        classUnderTest.add("Eric2");

        assertEquals("Daniel", classUnderTest.getElement("Daniel"));
        assertEquals("Tobias", classUnderTest.getElement("Tobias"));
        assertEquals("Eric", classUnderTest.getElement("Eric"));
        assertEquals("Daniel1", classUnderTest.getElement("Daniel1"));
        assertEquals("Tobias1", classUnderTest.getElement("Tobias1"));
        assertEquals("Eric1", classUnderTest.getElement("Eric1"));
        assertEquals("Daniel2", classUnderTest.getElement("Daniel2"));
        assertEquals("Tobias2", classUnderTest.getElement("Tobias2"));
        assertEquals("Eric2", classUnderTest.getElement("Eric2"));
    }


    @Test
    public void threeNodesDeleteEric() {

        classUnderTest.add("Daniel");
        classUnderTest.add("Tobias");
        classUnderTest.add("Eric");

        assertEquals("Eric", classUnderTest.remove("Eric"));
        assertEquals("Daniel", classUnderTest.getElement("Daniel"));
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

        classUnderTest.remove("Daniel");
        assertEquals("Daniel", classUnderTest.getElement("Daniel"));
    }

    @Test
    public void removeTwoDuplicate() {

        classUnderTest.add("Tobias");
        classUnderTest.add("Daniel");
        classUnderTest.add("Daniel");
        classUnderTest.add("Eric");

        classUnderTest.remove("Daniel");
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


}
