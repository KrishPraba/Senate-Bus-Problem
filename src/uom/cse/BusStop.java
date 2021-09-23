package uom.cse;

public class BusStop {
    public static final SharedResources SHARED_RESOURCES = new SharedResources();
    public static final long RIDER_ARRIVAL_TIME = 30000;
    public static final long BUS_ARRIVAL_TIME = 1200000;
    public static void main(String args[]) {
        new BusFactory().start();
//        new RiderFactory().start();
    }
}
