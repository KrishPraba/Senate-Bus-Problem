package uom.cse;

/**
 * Bus object threads that arrive to the bustop to pickup the riders
 */

public class Bus implements Runnable{

    private long id;
    private final int BOARDING_LIMIT = 50;

    public Bus(long id){
        this.id = id;
    }

    @Override
    public void run() {
        try {
            BusStop.MUTEX.acquire();
            System.out.println("Bus " + this.id +" arrived to the bus stop");
            int boardingRidersCount = Math.min(BusStop.WAITING_RIDERS.get(), BOARDING_LIMIT);
            for (int i = 0; i < boardingRidersCount; i++) {
                //Gives each rider in the boarding area , one by one, , a boarding pass to board the bus.
//                System.out.println("Bus semaphore released by Bus " + this.getId());
                BusStop.BUS.release();

                // The rider who has acquired the boarding pass can board the bus.
                // Wait for the rider boarded to release the boarding pass.
                BusStop.BOARDED.acquire();
//                System.out.println("Boarded semaphore acquired by Bus " + this.getId());
            }
            int n = BusStop.WAITING_RIDERS.get();

            //Set the remaining riders as waitingRiders after all (maximum 50) have boarded.
            BusStop.WAITING_RIDERS.set(Math.max(n - 50, 0));

            //release the boarding area entering pass
            BusStop.MUTEX.release();
//            System.out.println("Boarding area mutex released by Bus " + this.getId());
            depart();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    void depart() {
        System.out.println("Bus " + this.id + " departs");
    }


}
