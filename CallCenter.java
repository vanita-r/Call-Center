/**
 * Project 3 CallCenter class, it implements (for a simulation)
 * a call center, keeping track of incoming calls and answering
 * calls at the front of the queue when it their time.
 * 
 * @author Vanita Mae Rabsatt
 * @version Mar 28, 2025
 */
public class CallCenter
{
    /**
     * Instance variables for this class.
     * Define an array for the call queue,
     * and instance variable for the call
     * that is on the line, and counters
     * for the clock (time) and for the
     * number of elements in the queue.
     */
    //FIFO-like queue for regular calls. Stores 10 calls.
    //Array of 10 positions of the Call type.
    private Call[] callQueue;
    //Call has been answered and it is on the line right now.
    private Call callOnTheLine;
    //Simulation clock.
    private int clock;
    /**
     * Default constructor for the CallCenter, initializes
     * all instance variables with default values.
     */
    public CallCenter()
    {
        callQueue = new Call[10];
        clock = 0;
    }

    /**
     * Adds a call to the call queue.
     * 
     * @param n name of the caller.
     * @param v vip status of the caller.
     * @param d during of this call.
     * @return true if the call is added to the call queue, 
     * false otherwise.
     */
    public boolean addCall(String n, boolean v, int d)
    {
        if (n == null || n.trim().isEmpty()) {
            throw new IllegalArgumentException("Caller name cannot be null or empty.");
        }
        if (d <= 0) {
            throw new IllegalArgumentException("Duration must be positive.");
        }

        int size = 0;
        for (Call c: callQueue) {
            if (c != null) {
                size++;
            }
        }
        if (size >= callQueue.length) {
            //Queue is full.
            return false;
        }
        
        Call newCall = new Call(n, v, d);
        if (v) {
            //Shifts everything to the right.
            for (int i = size; i > 0; i--) {
                callQueue[i] = callQueue[i-1];
            }
            callQueue[0] = newCall;
        } else {
            //Adds newCall at the end.
            callQueue[size] = newCall;
        }
        return true;
    }

    /**
     * Simulates answering the call from the call
     * in the front of the queue.
     * @return null if an error is found, or the call 
     * object if answered.
     */
    public Call answerCall()
    {   
        //Active Call in progress.
        if (callOnTheLine != null) {
            return null;
        }

        //No calls waiting.
        if (numCallsWaiting() == 0) {
            return null;
        }

        //Answers the first call.
        callOnTheLine = callQueue[0];
        //Shifts remaining calls to the left.
        for (int i = 0; i < callQueue.length - 1; i++) {
            callQueue[i] = callQueue[i+1];
        }
        //Clears the last spot.
        callQueue[callQueue.length-1] = null;

        return callOnTheLine;
    }

    /**
     * The call center has a max capacity, at which point it
     * cannot take new calls until it answers and hangs up on
     * another call. This returns true if we are at capacity.
     * @return true if call center is full
     */
    public boolean isCallCenterAtCapacity()
    {
        for (Call c: callQueue) {
            if (c == null) {
                //If there's an empty spot.
                return false;
            }
        }
        //If all spots are filled.
        return true;
    }

    /**
     * Returns the number of calls waiting to be answered.
     * @return number of calls in queue
     */
    public int numCallsWaiting()
    {
        int count = 0;
        for (Call c: callQueue) {
            if (c != null) {
                count++;
            }
        }
        return count;
    }

    /**
     * Given a caller name, this returns the position in 
     * the queue for a caller. If there is no call with 
     * that name, then it returns -1. Worth noting that 
     * the call at the front of the queue is at position
     * 1 (but internally it is at index 0)
     * @param n name of the caller to search for
     * @return position (1..n) or -1 if not found
     */
    public int positionInLine(String n)
    {
        if (n == null || n.trim().isEmpty()) {
            return -1;
        }

        for (int i = 0; i < callQueue.length; i++) {
            if (callQueue[i] != null && callQueue[i].getName().equalsIgnoreCase(n)) {
                return i + 1;
            }
        }
        return -1;
    }

    /**
     * Is the call center busy, i.e., are they on a call
     * right now?
     * @return true if call center is on a call
     */
    public boolean isBusy()
    {
        if (callOnTheLine != null) {
            return true;
        } 
        return false;
    }

    /**
     * Returns the call object that is current on the call 
     * with the call center. If there is no current call, 
     * returns null.
     * @return call object for the current call.
     */
    public Call onTheLine()
    {
        if (isBusy()) {
            return callOnTheLine;
        } else {
            return null;
        }
    }

    /**
     * Simulates time passing. The clock ticks and decrements
     * the time left on the call for a call that is online.
     */
    public void clockTick()
    {
        clock++;
        if (callOnTheLine != null) {
            callOnTheLine.decrement();
            
            if (callOnTheLine.getDuration() <= 0) {
                //Ends the call if it's finished.
                callOnTheLine = null;
            }
        }
    }

    /**
     * Returns the current time in the simulation.
     * @return value of the clock
     */
    public int getClock()
    {
        return clock;
    }
}
