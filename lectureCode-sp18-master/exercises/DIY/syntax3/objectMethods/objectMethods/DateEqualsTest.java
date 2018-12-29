package objectMethods;
import org.junit.Test;
import static org.junit.Assert.*;

public class DateEqualsTest {
    @Test
    public void testEquals() {
        Date d1a = new Date(5, 10, 2010);
        Date d1b = new Date(5, 10, 2010);
        Date d2 = new Date(10, 11, 2012);
        assertEquals(d1a, d1a);
        assertEquals(d1a, d1b);
        assertNotEquals(d1a, d2);
        assertNotEquals(d1a, "horse");
        assertNotEquals(d1a, null);
    }
}
