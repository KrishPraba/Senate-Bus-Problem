package uom.cse;

/**
 * Rider object threads that arrive to the bustop to board the bus
 */

public class Rider implements Runnable
{
    long id;

    public Rider(long id){
        this.id = id;
    }

    @Override
    public void run() {
        try {

            //Enter the boarding area,one by one
            System.out.println("Rider " + this.id +" has arrived and is waiting to enter the boarding/waiting area");
            BusStop.MUTEX.acquire();
            System.out.println("Rider " + this.id +" has entered the boarding/waiting area");

            //Increment the waitingRiders count by 1
            BusStop.WAITING_RIDERS.incrementAndGet();

            //Release the "mutex", allowing others Riders to enter the waiting area
            BusStop.MUTEX.release();

            //Wait till a Bus signals the rider to board the bus.
            BusStop.BUS.acquire();

            //boarding the bus.
            boardBus();

            //Notify the bus that the Rider has boarded, allowing others to board
            BusStop.BOARDED.release();

        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    void boardBus() { System.out.println("Rider " + this.id+ " is boarding the bus"); }
}
