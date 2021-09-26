package uom.cse;

import java.util.Random;

/**
 * Spawning a Bus Factory thread that is incharge of creating bus objects at an exponentially
 * distributed inter arrival times with a mean of 20 minutes
 */

public class BusFactory implements Runnable{

    private static Random random;
    private float meanArrivalTime;

    public BusFactory(){
        this.meanArrivalTime = BusStop.BUS_ARRIVAL_TIME;
        random = new Random();
    }

    @Override
    public void run() {

        long id = 0;

        while (!Thread.currentThread().isInterrupted()) {
            long produceInterval = getExponentiallyDistributedBusInterArrivalTime();

            id++;

            try {

                System.out.println("Next bus arrives in " + (produceInterval/60000) + " minutes");

                Thread.sleep(produceInterval);

                Bus bus = new Bus(id);
                (new Thread(bus)).start();

            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public long getExponentiallyDistributedBusInterArrivalTime() {
        float lambda = 1 / meanArrivalTime;
        return Math.round(-Math.log(1 - random.nextFloat()) / lambda);
    }
}
