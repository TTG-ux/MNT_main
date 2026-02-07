package THREAD.Tasks.LoadBalancer;

import java.net.URI;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Balancer {
    private static final int MAX_INSTANCES = 10;
    private final CopyOnWriteArrayList<URI> instances = new CopyOnWriteArrayList<>();
    private final Strategy strategy;
    private final AtomicInteger roundRobinIndex = new AtomicInteger(0);

    public enum Strategy {
        RANDOM,
        ROUND_ROBIN
    }

    public Balancer(Strategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("Strategy cannot be null");
        }
        this.strategy = strategy;
    }

        public boolean register(URI instance) throws TooManyInstancesException {
        if (instance == null) {
            throw new IllegalArgumentException("Instance cannot be null");
        }
        
                if (instances.size() >= MAX_INSTANCES) {
            throw new TooManyInstancesException(
                "Cannot register instance: limit of " + MAX_INSTANCES + " reached"
            );
        }
        
                return instances.addIfAbsent(instance);
    }

    public URI get() {
        int size = instances.size();
        if (size == 0) {
            throw new IllegalStateException("No instances registered");
        }

        switch (strategy) {
            case ROUND_ROBIN:
                // Атомарный инкремент с модулем — потокобезопасно без блокировок
                int index = Math.floorMod(roundRobinIndex.getAndIncrement(), size);
                return instances.get(index);
                
            case RANDOM:
            default:
                int randomIndex = ThreadLocalRandom.current().nextInt(size);
                return instances.get(randomIndex);
        }
    }

    public int size() {
        return instances.size();
    }

    public void clear() {
        instances.clear();
    }

    
    public class TooManyInstancesException extends Exception {
        public TooManyInstancesException(String message) {
            super(message);
        }
    }
}

