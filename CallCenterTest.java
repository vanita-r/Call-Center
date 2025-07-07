//import itsc2214.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Tests multiple cases/scenarios that could happen
 * if methods from the CallCenter class are called.
 * 
 * @author  Vanita Mae Rabsatt
 * @version Mar 28, 2025
 */
public class CallCenterTest {
    /**
     * Tests the constructor and its default values.
     */
    @Test
    public void testConstructorDefaults() {
        CallCenter center = new CallCenter();
        assertEquals(0, center.getClock());
        assertFalse(center.isCallCenterAtCapacity());
        assertNull(center.onTheLine());
        assertEquals(0, center.numCallsWaiting());
        assertFalse(center.isBusy());
    }

    /**
     * Tests clockTick and getClock.
     */
    @Test
    public void testClockTick() {
        CallCenter center = new CallCenter();
        assertEquals(0, center.getClock());
        center.clockTick();
        assertEquals(1, center.getClock());
    }

    /**
     * Adds some calls and tests queue.
     */
    @Test
    public void testAddCallAndQueueState() {
        CallCenter center = new CallCenter();
        assertTrue(center.addCall("Sam", false, 10));
        assertTrue(center.addCall("Bob", false, 20));

        assertFalse(center.isCallCenterAtCapacity());
        assertEquals(2, center.numCallsWaiting());
        assertFalse(center.isBusy());

        assertEquals(1, center.positionInLine("Sam"));
        assertEquals(2, center.positionInLine("Bob"));
        assertEquals(-1, center.positionInLine("Amy"));
    }

    /**
     * Fill queue to capacity and test its behavior.
     */
    @Test
    public void testFullQueue(){
        CallCenter center = new CallCenter();
        for (int i = 0; i < 10; i++) {
            assertTrue(center.addCall("Bob", false, 7));
        }

        assertTrue(center.isCallCenterAtCapacity());
        assertEquals(10, center.numCallsWaiting());
        assertFalse(center.isBusy());
        assertEquals(1, center.positionInLine("Bob"));
        assertEquals(-1, center.positionInLine("Amy"));

        assertFalse(center.addCall("Bob", false, 7));
    }

    /**
     * Ensures VIP calls are added to the front.
     */
    @Test
    public void testVipPriorityQueueing() {
        CallCenter center = new CallCenter();
        assertTrue(center.addCall("Bob", false, 8));
        assertTrue(center.addCall("Sam", true, 10));

        assertEquals(2, center.numCallsWaiting());
        assertEquals(2, center.positionInLine("Bob"));
        assertEquals(1, center.positionInLine("Sam"));
        assertEquals(-1, center.positionInLine("Mae"));
    }

    /**
     * Test answerCall with various states.
     */
    @Test
    public void testAnswerCallScenarios() {
        CallCenter center = new CallCenter();

        //No calls yet.
        assertNull(center.answerCall());

        //Adds a call and answers.
        assertTrue(center.addCall("Amy", false, 3));
        Call answered = center.answerCall();
        assertNotNull(answered);
        assertEquals("Amy", answered.getName());
        assertTrue(center.isBusy());
        assertEquals(0, center.numCallsWaiting());

        //Already on a call and can't answer another one.
        assertTrue(center.addCall("Amy", false, 4));
        assertNull(center.answerCall());

        //Tick until the first call ends.
        center.clockTick(); 
        center.clockTick();
        center.clockTick(); 

        assertFalse(center.isBusy());
        assertEquals(1, center.numCallsWaiting());

        //Answer the next call.
        Call next = center.answerCall();
        assertNotNull(next);
        assertEquals("Amy", next.getName());
    }
}
