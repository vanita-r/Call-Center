//import itsc2214.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Tests multiple cases/scenarios that could happen
 * if methods from the Call class are called.
 * 
 * @author  Vanita Mae Rabsatt
 * @version Mar 28, 2025
 */
public class CallTest {
    /**
     * Tests the default Call constructor.
     */
    @Test
    public void testDefaultConstructor() {
        Call call = new Call();

        assertNotNull(call.getName());
        assertEquals("Unknown Caller", call.getName());
        assertFalse(call.isVIP());
        assertEquals(10, call.getDuration());
    }

    /**
     * Tests the constructor with known caller, priority, and 30 second duration.
     */
    @Test
    public void testConstructor() {
        Call call = new Call("Bob", true, 30);

        assertNotNull(call.getName());
        assertEquals("Bob", call.getName());
        assertTrue(call.isVIP());
        assertEquals(30, call.getDuration());
    }

    /**
     * Tests constructor with a duration of 0 seconds, known caller, and priority.
     */
    @Test
    public void testConstructor2() {
        Call call = new Call("Amy", true, 0);

        assertNotNull(call.getName());
        assertEquals("Amy", call.getName());
        assertTrue(call.isVIP());
        //Ensure it returns 1 instead of 0.
        assertEquals(1, call.getDuration());
    }

    /**
     * Tests constructor with a null name.
     */
    @Test
    public void testNullNameInConstructor() {
        Call call = new Call(null, true, 20);

        assertEquals("Unknown Caller", call.getName());
        //Ensure it returns false instead of true.
        assertFalse(call.isVIP());
        assertEquals(20, call.getDuration());
    }

    /**
     * Tests constructor with an empty name.
     */
    @Test
    public void testEmptyNameInConstructor() {
        Call call = new Call("", true, 15);

        assertEquals("Unknown Caller", call.getName());
        //Ensure it returns false instead of true.
        assertFalse(call.isVIP());
        assertEquals(15, call.getDuration());
    }

    /**
     * Tests constructor with a negative duration.
     */
    @Test
    public void negativeDurationInConstructor() {
        Call call = new Call("Sam", true, -10);

        assertEquals("Sam", call.getName());
        assertTrue(call.isVIP());
        //Ensure it returns 1 instead of -10.
        assertEquals(1, call.getDuration());
    }
    
    /**
     * Tests the getName() method and ensures it returns the name.
     */
    @Test
    public void testGetName() {
        Call call = new Call("Bob", true, 10);
        assertNotNull(call.getName());
        assertEquals("Bob", call.getName());
    }

    /**
     * Tests isVIP() with a known caller and ensures priority is true.
     */
    @Test
    public void testIsVIP() {
        Call call = new Call("Mae", true, 10);
        assertTrue(call.isVIP());
    }

    /**
     * Tests isVIP() with an unknown calller and ensures priority is false.
     */
    @Test
    public void testIsVIPUnknownCaller() {
        Call call = new Call(null, true, 10);
        assertFalse(call.isVIP());
    }
    
    /**
     * Tests getDuration() and ensures it returns the correct time.
     */
    @Test
    public void testGetDuration() {
        Call call = new Call("Bob", true, 60);
        assertEquals(60, call.getDuration());
    }

    /**
     * Ensures getDuration() returns 1 when given a negative duration.
     */
    @Test
    public void testGetDurationNegative() {
        Call call = new Call("Cat", true, -56);
        assertEquals(1, call.getDuration());
    }

    /**
     * Tests decrement() and ensures it subtracts 1 from duration when called.
     */
    @Test
    public void testDecrement() {
        Call call = new Call("Tim", true, 3);
        call.decrement();
        assertEquals(2, call.getDuration());
        call.decrement();
        assertEquals(1, call.getDuration());
        call.decrement();
        assertEquals(0, call.getDuration());
    }

}
