package uom.cse;

public class Bus extends Thread{
    private final int BOARDING_LIMIT = 50;

    @Override
    public void run() {
        try {
            /**
             * Acquire the "mutex" so that only riders that had already arrived
             * before the arrival of bus are able to board the bus.
             */
            BusStop.SHARED_RESOURCES.getMutex().acquire();
            System.out.println("Bus " + this.getId() +" arrived");
            int boardingRidersCount = Math.min(BusStop.SHARED_RESOURCES.getWaitingRiders().get(), BOARDING_LIMIT);
            for (int i = 0; i < boardingRidersCount; i++) {
                //Signals each rider the boarding pass, now a rider who has  acquired the boarding pass(Bus semaphore) can board.
                System.out.println("Bus semaphore released by Bus " + this.getId());
                BusStop.SHARED_RESOURCES.getBus().release();
                //Wait for the rider to release the boarded semaphore.
                BusStop.SHARED_RESOURCES.getBoarded().acquire();
                System.out.println("Boarded semaphore acquired by Bus " + this.getId());
            }
            int n = BusStop.SHARED_RESOURCES.getWaitingRiders().get();
            //Set the remaining riders as waitingRidersCount after all have boarded.
            BusStop.SHARED_RESOURCES.getWaitingRiders().set(Math.max(n - 50, 0));
            //release the boarding area entering pass(mutex)
            BusStop.SHARED_RESOURCES.getMutex().release();
            System.out.println("Boarding area mutex released by Bus " + this.getId());
            depart();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    void depart() {
        System.out.println("Bus " + this.getId() + " departs");
    }


}
