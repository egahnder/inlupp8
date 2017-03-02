/**
 * Created by loxtank on 2017-03-02.
 */
import org.junit.Test;
import static org.junit.Assert.*;

public class SkipListTest {

    @Test public void oneNode() {
        SkipList classUnderTest = new SkipList();

        classUnderTest.add("Daniel");

        assertEquals("Daniel", classUnderTest.getElement("Daniel"));
    }

    @Test public void twoNodesDaniel() {
        SkipList classUnderTest = new SkipList();

        classUnderTest.add("Daniel");
        classUnderTest.add("Tobias");

        assertEquals("Daniel", classUnderTest.getElement("Daniel"));
    }

    @Test public void twoNodesTobias() {
        SkipList classUnderTest = new SkipList();

        classUnderTest.add("Daniel");
        classUnderTest.add("Tobias");

        assertEquals("Tobias", classUnderTest.getElement("Tobias"));
    }

    @Test public void threeNodesTobias() {
        SkipList classUnderTest = new SkipList();

        classUnderTest.add("Daniel");
        classUnderTest.add("Tobias");
        classUnderTest.add("Eric");

        assertEquals("Tobias", classUnderTest.getElement("Tobias"));
    }

    @Test public void threeNodesEric() {
        SkipList classUnderTest = new SkipList();

        classUnderTest.add("Daniel");
        classUnderTest.add("Tobias");
        classUnderTest.add("Eric");

        assertEquals("Eric", classUnderTest.getElement("Eric"));
    }

    @Test public void fourNodesDaniel() {
        SkipList classUnderTest = new SkipList();

        classUnderTest.add("Daniel");
        classUnderTest.add("Tobias");
        classUnderTest.add("Eric");
        classUnderTest.add("Anton");

        assertEquals("Daniel", classUnderTest.getElement("Daniel"));
    }

    @Test public void nineNodes() {
        SkipList classUnderTest = new SkipList();

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
        SkipList classUnderTest = new SkipList();

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



}
