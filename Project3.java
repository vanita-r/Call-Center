import java.util.Random;
/**
 * Project 3 - simulation of a call center.
 */
public class Project3 {

    /**
     * Main program calls the CallCenter
     * simulating the incoming of events.
     */
    public static void main(String[] args) {

    	// Call center for the Mos Eisley Cantina
        CallCenter cantina = new CallCenter();
        Random random = new Random();
        
        cantina.addCall("Luke", false, 7);
        cantina.addCall("Leia", false, 5);
        cantina.addCall("C3PO", false, 25);
        cantina.addCall("Kylo", random.nextBoolean(), 10);

        // Simulation runs while there are callers waiting
        // or there is someone on the line.    
        while ((cantina.numCallsWaiting() > 0) || cantina.isBusy()) {
            // increase the simulation clock
            cantina.clockTick();

            // if the line is busy, then find who is on the line
            // and print a message
            if (cantina.isBusy()) {
                Call ac = cantina.onTheLine();
                System.out.println(cantina.getClock()+": "+
                    ac.getName()+" on the line, "+ac.getDuration()+" secs left");
            }
            else {
                // Else, there is nobody on the line, so
                // answer the next caller.
                Call ac = cantina.answerCall();	// get next call
                if (ac != null) {
                    System.out.println(cantina.getClock()+": "+
                    	"Mos Eisley Cantina, can I help you? "+
                    	ac.getName()+" calling, "+ac.getDuration()+" secs call");
                }
            }

            // We might have a random prank caller but only 10% of the time,
            // and only if they are not on the line already or waiting in the queue
            if ((Math.random() < 0.1) &&
                (cantina.onTheLine().getName().compareTo("Vader") != 0) && 
                (cantina.positionInLine("Vader") == -1)) {
                // then add a short call, from Vaderm, he wants to say is
                // "I am your father" and hangs up.
                cantina.addCall("Vader", true, 3);
            }
        }
    }
}
