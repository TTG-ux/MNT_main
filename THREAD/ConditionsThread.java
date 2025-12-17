package THREAD;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionsThread {

    public static void main(String[] args) {
        
        Store store = new Store();

        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}

//Класс магазин, хронящий произведенные товары
class Store{

    private int product = 0;
    ReentrantLock locker;
    Condition condition;

    Store(){

        locker = new ReentrantLock(); //Создаем блокировку
        condition = locker.newCondition(); //Получаем условие, связанное с блокировкой
    }

    public void get(){

        locker.lock();

        try {
            //Пока нет доступных товаров на складе ожидаем
            while (product < 1)
                condition.await();

            product--;
            
            System.out.println("Покупатель купил 1 товар");
            System.out.println("Товаров на складе: " + product);

            //Сигнализируем
            condition.signalAll();

        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        finally {
            locker.unlock();
        }
    }

    public void put(){

        locker.lock();

        try {
            //Пока на складе 3 товара, ожидаем
            while (product >= 50)
                condition.await();

            product++;

            System.out.println("Производитель добавил 1 товар");
            System.out.println("Товаров на складе: " + product);

            //Сигнализируем
            condition.signalAll();

        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        finally {
            locker.unlock();
        }
    }
}

// класс Производитель
class Producer implements Runnable{
  
    Store store;
    Producer(Store store){
       this.store=store; 
    }
    public void run(){
        for (int i = 1; i < 100; i++) {
            store.put();
        }
    }
}

//Класс потребитель
class Consumer implements Runnable {

    Store store;

    Consumer(Store store) {
        this.store = store;
    }

    public void run() {
        for (int i = 1; i <= 6; i++) {
            store.get();
        }
    }
}