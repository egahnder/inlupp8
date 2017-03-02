/**
 * Created by loxtank on 2017-03-02.
 */
import org.junit.Test;
import static org.junit.Assert.*;

public class SkipListTest {

    @Test public void OneNode() {
        SkipList classUnderTest = new SkipList();

        classUnderTest.add("Daniel");

        assertEquals("Daniel", classUnderTest.getElement("Daniel"));
    }

    @Test public void TwoNodesDaniel() {
        SkipList classUnderTest = new SkipList();

        classUnderTest.add("Daniel");
        classUnderTest.add("Tobias");

        assertEquals("Daniel", classUnderTest.getElement("Daniel"));
    }

    @Test public void TwoNodesTobias() {
        SkipList classUnderTest = new SkipList();

        classUnderTest.add("Daniel");
        classUnderTest.add("Tobias");

        assertEquals("Tobias", classUnderTest.getElement("Tobias"));
    }

}
