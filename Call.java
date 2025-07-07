/**
 * Project 3 - Call class, used to model a caller
 * into a call center. It stores name of the caller,
 * VIP status and duration of the call.
 * 
 * @author  Vanita Mae Rabsatt
 * @version March 28, 2025
 */
public class Call 
{
    /**
     * declare instance variables for name, vip, 
     * and duration.
     */
    //Name of caller.
    private String callerName;
    //TRUE for VIP callers, FALSE for regular callers.
    private boolean priority; 
    //Estimated duration of the call in seconds.
    private int duration;

    /**
     * Describe the default constructor.
     */
    public Call()
    {
        //Default caller name.
        callerName = "Unknown Caller";
        //Default priority.
        priority = false;
        //Default duration.
        duration = 10;
    }

    /**
     * Constructor that initializes all instance variables
     * with values passed as parameters.
     * @param name name of the caller
     * @param priority boolean true if caller is VIP, false otherwise
     * @param duration length of the call in fake units
     */
    public Call(String name, boolean priority, int duration) {
        if (name == null || name.isEmpty()) {
            this.callerName = "Unknown Caller";
            this.priority = false;
        } else {
            this.callerName = name;
            this.priority = true;
        }

        if (duration <= 0) {
            this.duration = 1;
        } else {
            this.duration = duration;
        }
    }

    /**
     * A method that returns the name of the caller.
     * @return caller name of the caller
     */
    public String getName()
    {
        return callerName;
    }

    /**
     * A method that returns the priority of the call.
     * @return priority of call
     */
    public boolean isVIP()
    {
        return priority;
    }

    /**
     * A method that returns the duration of the call.
     * @return duration of call
     */
    public int getDuration()
    {
        return duration;
    }
    
    /**
     * Subtracts 1 from the duration to reach completion of call.
     */
    public void decrement()
    {
        duration--;
    }
}
