package THREAD;

import java.util.concurrent.atomic.LongAdder;

// public class AtomicsThread {
    
//     public static void main(String[] args) throws InterruptedException{
          
//         int numThreads = 1000;          // количество потоков
//         int incrementsPerThread = 1000; // количество итреаций в каждом потоке
 
//         Counter counter = new Counter();
  
//         Thread[] threads = new Thread[numThreads];
//         for (int i = 0; i < numThreads; i++){
              
//             threads[i] = new Thread(() ->{ 
 
//                 for (int j = 0; j < incrementsPerThread; j++) {
//                     counter.increment();
//                 }
                 
//             });
//             threads[i].start();
//         }
//         // небольшая задержка в 1 секунду, чтобы потоки успели поработать
//         Thread.sleep(1000);
 
//         // ожидаем завершения всех потоков
//         for (int i = 0; i < numThreads; i++){
//             threads[i].join();
//         }
 
//         // проверяем значение счетчика
//         System.out.println("Counter: " + counter.getCounter());
//     }
// }

// class Counter {
    
//     private volatile long counter = 0;

//     long getCounter() {
//         return counter;
//     }

//     void increment() {
//         counter++;
//     }
// }


// Корректируем код выше, чтобы использовать AtomicLong для обеспечения атомарности операций над счетчиком.
public class AtomicsThread {
    
    public static void main(String[] args) throws InterruptedException{
          
        int numThreads = 1000;          // количество потоков
        int incrementsPerThread = 1000; // количество итреаций в каждом потоке
 
        Counter counter = new Counter();
  
        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++){
              
            threads[i] = new Thread(() ->{ 
 
                for (int j = 0; j < incrementsPerThread; j++) {
                    counter.increment();
                }
                 
            });
            threads[i].start();
        }
        // небольшая задержка в 1 секунду, чтобы потоки успели поработать
        Thread.sleep(1000);
 
        // ожидаем завершения всех потоков
        for (int i = 0; i < numThreads; i++){
            threads[i].join();
        }
 
        // проверяем значение счетчика
        System.out.println("Counter: " + counter.getCounter());
    }
}

class Counter {
    
    private LongAdder counter = new LongAdder();

    long getCounter() {
        return counter.longValue(); // возвращаем сумму всех значений (инкрементов) потоков
    }

    void increment() {
        counter.increment(); //  увеличиваем на 1
    }
}
