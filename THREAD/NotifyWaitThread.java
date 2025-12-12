package THREAD;

public class NotifyWaitThread {
    
    public static void main(String[] args) {
        
        Store store = new Store();

        Producer producer = new Producer(store);

        Consumer consumer = new Consumer(store);

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}

//Класс магазин, хранящий производные товары
class Store{

    private int product = 0;

    public synchronized void get(){

        while(product < 1){

            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

        product--;

        System.out.println("Покупатель купил 1 товар");
        System.out.println("Товаров на складе: " + product);
        notify();
    }

    public synchronized void put(){

        while (product > 1){
            
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

        product++;

        System.out.println("Продавец добавил 1 товар");
        System.out.println("Товаров на складе: " + product);
        notify();
    }
}

//Класс производитель
class Producer implements Runnable{

    Store store;

    Producer(Store store){
        this.store = store;
    }
    
    public void run(){

        for(int i = 1; i < 6; i++){

            store.put();
        }
    }
}

// Класс Потребитель
class Consumer implements Runnable{
      
     Store store;
    Consumer(Store store){
       this.store=store; 
    }
    public void run(){
        for (int i = 1; i < 6; i++) {
            store.get();
        }
    }
}