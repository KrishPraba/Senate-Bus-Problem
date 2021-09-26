package uom.cse;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The bus stop that regulates the arrival of the bus and riders, boarding of the riders into the bus
 * and the departure of the bus.
 *
 * Follows the, 'I'll do it for you approach"
 */

public class BusStop {

    //Inter-arrival times for both buses and riders
    public static final float RIDER_ARRIVAL_TIME = 30f * 1000;
    public static final float BUS_ARRIVAL_TIME = 20 * 60f * 1000;

    //Number of riders in the boarding area
    //(Using AtomicInteger to achieve thread safety with the operations on the integer)
    public static final AtomicInteger WAITING_RIDERS = new AtomicInteger(0);

    //Protects the boarding area
    public static final Semaphore MUTEX = new Semaphore(1);

    //Protects riders arriving after bus arrives to board the bus
    public static final Semaphore BUS = new Semaphore(0);

    //Protects the riders boarding the bus.
    public static final Semaphore BOARDED = new Semaphore(0);

    public static void main(String args[]) {
        printTitle();

        RiderFactory riderFactory = new RiderFactory();
        (new Thread(riderFactory)).start();

        BusFactory busFactory = new BusFactory();
        (new Thread(busFactory)).start();


    }

    private static void printTitle(){
        System.out.println();
        System.out.printf("%100s\n","*********************************");
        System.out.printf("%100s\n","*    *                     *    *");
        System.out.printf("%100s\n","*    *                     *    *");
        System.out.printf("%100s\n","****** SENATE BUS PROBLEM  ******");
        System.out.printf("%100s\n","*    *                     *    *");
        System.out.printf("%100s\n","*    *                     *    *");
        System.out.printf("%100s\n","*********************************");
        System.out.println();


    }
}
