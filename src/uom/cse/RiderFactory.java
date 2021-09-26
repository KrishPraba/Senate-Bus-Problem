package uom.cse;

import java.util.Random;

/**
 * Spawning a Rider Factory thread that is incharge of creating rider objects at an exponentially
 * distributed inter arrival times with a mean of 30 seconds
 */

public class RiderFactory implements Runnable {

    private static Random random;
    private float meanArrivalTime;

    public RiderFactory(){
        this.meanArrivalTime = BusStop.RIDER_ARRIVAL_TIME;
        random = new Random();
    }

    @Override
    public void run() {

        long id = 0;

        while(!Thread.currentThread().isInterrupted()) {
            long produceInterval = getExponentiallyDistributedRiderInterArrivalTime();

            id++;

            try {

                Rider rider = new Rider(id);
                (new Thread(rider)).start();


                System.out.println("Next rider arrives in " + (produceInterval/1000) + " seconds");


                Thread.sleep(produceInterval);


            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public long getExponentiallyDistributedRiderInterArrivalTime() {
        float lambda = 1 / meanArrivalTime;
        return Math.round(-Math.log(1 - random.nextFloat()) / lambda);
    }
}
