package uom.cse;

public class BusFactory extends Thread{
    @Override
    public void run() {
        long produceInterval = 0;

        while (true) {
//            produceInterval = (long) (-(Math.log(Math.random())) * BusStop.BUS_ARRIVAL_TIME);
            produceInterval = (long) Math.round(Math.log10(Math.random())*-1* BusStop.BUS_ARRIVAL_TIME);
            System.out.println("Next bus arrives in " + produceInterval + " milliseconds");

            try {
                Thread.sleep(produceInterval);
                new Bus().start();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
