package THREAD.Tasks.EntryLevelCounter;

import static java.lang.System.nanoTime;
import static java.lang.System.out;
import static java.lang.Thread.sleep;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    
    // Небезопастный счетчик
    static class UnsafeCounter {
        private int count = 0;

        public void increment() {
            count++; // Может вызвать read-notify-write
        }

        public int getCount() {
            return count;
        }
    }

    // Безопасные счетчики

    // 1
    static class SynchronizedCounter {
        private int counter = 0;

        public int getCounter() {
            return counter;
        }

        public synchronized void increment() {
            counter++;
        }
    }

    // 2, volatile
    static class VolatileCounter {
        private volatile int counter = 0;

        public int getCounter() {
            return counter;
        }

        // Volatile горантирует видимость, но не гарантирует атомарность составной операции
        public void increment() {
            counter++; // запись + чтение + инкрементность != атомарность
        }
    }

    // 3 AtomicInteger
    static class AtomicCounter {
        private final AtomicInteger count = new AtomicInteger(0);

        public int getCount() {
            return count.get();
        }

        public void increment() {
            count.incrementAndGet(); // Атомарная операция через CAS (Compare And Swap)
        }
    }

    // 4 RentrantLock
    static class LockedCounter {
        private int count = 0;

        public int getCount() {
            return count;
        }

        private final ReentrantLock lock = new ReentrantLock();

        public void increment() {
            lock.lock();
            try {
                count++;
            } finally {
                lock.unlock();
            }
        }
    }

    // Вспомогательный метод для запуска методов
    private static void runTest(String name, Runnable incrementAction, Runnable getResult)
        throws InterruptedException {

        long start = nanoTime();

        // Создаем и запускаем к примеру 10 потоков
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    incrementAction.run();
                    // Делаем паузу для гонки
                    if (Math.random() < 0.001) Thread.yield();
                }
            });
            threads[i].start();
        }

        // Ждем завершения всех потоков
        for (Thread t : threads)
            t.join(); // Join пробрасывает 'InterruptedException', который нужно обработать в начале метода

        long duration = nanoTime() - start;

        getResult.run();
        out.printf("%-25s | Время: %6d мкс | Результат: %5d (Ожидалось 10000)%n",
                          name, duration / 1000,
                          name.contains("Unsafe") || name.contains("Volatail")
                          ? ((UnsafeCounter) unsafeCounter).getCount()
                          : 0);
    }

    // Глобальные экзепляры для тестов
    static UnsafeCounter unsafeCounter;
    static SynchronizedCounter syncCounter;
    static VolatileCounter volatileCounter;
    static AtomicCounter atomicCounter;
    static LockedCounter lockedCounter;


    public static void main(String[] args) throws InterruptedException {
        
        out.println("=".repeat(70));
        out.println("Демонстрация гонки".repeat(70));
        out.println("=".repeat(70));

        // ТЕСТ 1
        out.println("\nСчетчик без синхронизации (ОПАСНЫЙ)");
        unsafeCounter = new UnsafeCounter();
        // Пробрасывает 'InterruptedException' который нужно обработать в начале метода
        runTest("UnsafeCounter",
                      () -> unsafeCounter.increment(),
                      () -> out.printf(" Итог: %d%n", unsafeCounter.getCount()));

        
        // ТЕСТ 2
        out.println("\n Volatile (видимость есть, атомарности НЕТ):");

        volatileCounter = new VolatileCounter();
        runTest("VolatileCounter",
                        () -> volatileCounter.increment(),
                        () -> out.printf(" Итог: %d%n", volatileCounter.getCounter()));

        
        // ТЕСТ 3
        out.println("\n Synchronized (потокобезопасный)");

        syncCounter = new SynchronizedCounter();
        long syncStart = nanoTime();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++)
                    syncCounter.increment();
            }).start();
        }

        // Ждем завершения
        sleep(100); // Спим 100 млсек

        long syncTime = nanoTime() - syncStart;
        out.printf(" Итог: %d%n | Время: %d мкс%n", syncCounter.getCounter(), syncTime / 1000);

        
        // ТЕСТ 4
        out.println("\n AtomicInteger (потокобезопасный)");
        atomicCounter = new AtomicCounter();
        long atomicStart = nanoTime();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++)
                    atomicCounter.increment();
            }).start();
        }
        
        sleep(100);

        long atomicTime = nanoTime() - atomicStart;
        out.printf(" Итог: %d | Время: %d мкс%n", atomicCounter.getCount(), atomicTime / 1000);


        // ТЕСТ 5
        System.out.println("\n REENTRANTLOCK:");
        lockedCounter = new LockedCounter();
        long lockStart = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) lockedCounter.increment();
            }).start();
        }
        Thread.sleep(100);
        long lockTime = System.nanoTime() - lockStart;
        System.out.printf("   Итог: %d | Время: %d мкс%n", lockedCounter.getCount(), lockTime / 1000);
    }
}
