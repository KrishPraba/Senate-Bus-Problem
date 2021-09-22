package uom.cse;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SharedResources
{
    //Number of riders in the boarding area
    //(Using AtomicInteger to achieve thread safety with the operations on the integer)
    private final AtomicInteger waitingRiders = new AtomicInteger(0);

    //Protects the boarding area
    private final Semaphore mutex = new Semaphore(1);

    //Protects riders arriving after bus arrives to board the bus
    private final Semaphore bus = new Semaphore(0);

    //Protects the riders boarding the bus.
    private final Semaphore boarded = new Semaphore(0);

    public AtomicInteger getWaitingRiders()
    {
        return waitingRiders;
    }

    public Semaphore getMutex()
    {
        return mutex;
    }

    public Semaphore getBus()
    {
        return bus;
    }

    public Semaphore getBoarded()
    {
        return boarded;
    }
}