package THREAD.Tasks.LoadBalancer;

import java.net.URI;
import java.net.URISyntaxException;

import THREAD.Tasks.LoadBalancer.Balancer.TooManyInstancesException;

public class Main {
    
    public static void main(String[] args) throws TooManyInstancesException, URISyntaxException {
        
    Balancer lb = new Balancer(Balancer.Strategy.ROUND_ROBIN);

    lb.register(new URI("urn:isbn:0-486-27557-4"));
    lb.register(new URI("https://www.guru99.com/rust-load-balancing.html"));

    URI instance1 = lb.get(); 
    URI instance2 = lb.get(); 
    URI instance3 = lb.get(); 
    }
}