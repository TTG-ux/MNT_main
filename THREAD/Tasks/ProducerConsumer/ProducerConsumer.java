package THREAD.Tasks.ProducerConsumer;

import static java.lang.System.out;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ProducerConsumer {

    private static final int QUEUE_CAPACITY = 10;
    private static final int PRODUCER_COUNT = 3;
    private static final int CONSUMER_COUNT = 2;
    private static final int PRODUCTION_TIME_MS = 200;  
    private static final int CONSUMPTION_TIME_MS = 300; 
    private static final int RUN_DURATION_MS = 1000;    // Время работы

    private static final AtomicInteger produced = new AtomicInteger(0);
    private static final AtomicInteger consumed = new AtomicInteger(0);

    // Производитель
    static class Producer implements Runnable {
        private final BlockingQueue<Integer> queue;
        private final int id;
        private final int productionDelay;
        private volatile boolean running = true;

        Producer(BlockingQueue<Integer> queue, int id, int productionDelay) {
            this.queue = queue;
            this.id = id;
            this.productionDelay = productionDelay;
        }

        @Override
        public void run() {
            try {
                while (running && !Thread.currentThread().isInterrupted()) {
                    int item = ThreadLocalRandom.current().nextInt(100);
                    queue.put(item); // Блокируется если очередь заполнена
                    
                    int count = produced.incrementAndGet();
                    out.printf("[Producer-%d] Produced: %3d | Total: %4d | Queue: %2d/%d%n",
                            id, item, count, queue.size(), QUEUE_CAPACITY);
                    
                    Thread.sleep(productionDelay);
                }
            } catch (final InterruptedException InterruptedException) {
                Thread.currentThread().interrupt(); 
            }
        }

        void stop() {
            running = false;
        }
    }

    // Потребитель
    static class Consumer implements Runnable {
        private final BlockingQueue<Integer> queue;
        private final int id;
        private final int consumptionDelay;
        private volatile boolean running = true;

        Consumer(BlockingQueue<Integer> queue, int id, int consumptionDelay) {
            this.queue = queue;
            this.id = id;
            this.consumptionDelay = consumptionDelay;
        }

        @Override
        public void run() {
            try {
                while (running && !Thread.currentThread().isInterrupted()) {
                    Integer item = queue.take(); // Блокируется если очередь пуста
                    
                    int count = consumed.incrementAndGet();
                    out.printf("[Consumer-%d] Consumed: %3d | Total: %4d | Queue: %2d/%d%n",
                            id, item, count, queue.size(), QUEUE_CAPACITY);
                    
                    Thread.sleep(consumptionDelay); // Имитация обработки
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        void stop() {
            running = false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(QUEUE_CAPACITY);

        // Запускаем производителей с разной скоростью
        Producer[] producers = new Producer[PRODUCER_COUNT];
        Thread[] producerThreads = new Thread[PRODUCER_COUNT];
        
        for (int i = 0; i < PRODUCER_COUNT; i++) {
            int delay = PRODUCTION_TIME_MS - i * 30;
            producers[i] = new Producer(queue, i + 1, Math.max(50, delay));
            producerThreads[i] = new Thread(producers[i], "Producer-" + (i + 1));
            producerThreads[i].start();
        }

        // Запускаем потребителей с разной скоростью
        Consumer[] consumers = new Consumer[CONSUMER_COUNT];
        Thread[] consumerThreads = new Thread[CONSUMER_COUNT];
        
        for (int i = 0; i < CONSUMER_COUNT; i++) {
            int delay = CONSUMPTION_TIME_MS + i * 50;
            consumers[i] = new Consumer(queue, i + 1, delay);
            consumerThreads[i] = new Thread(consumers[i], "Consumer-" + (i + 1));
            consumerThreads[i].start();
        }

        // Работаем заданное время
        Thread.sleep(RUN_DURATION_MS);

        // Корректная остановка
        out.println("\n" + "-".repeat(70));
        out.println("ЗАВЕРШЕНИЕ РАБОТЫ...");
        out.println("-".repeat(70));

        // Сигнализируем потокам о завершении
        for (Producer p : producers) p.stop();
        for (Consumer c : consumers) c.stop();

        // Прерываем потоки для выхода из блокирующих операций
        for (Thread t : producerThreads) t.interrupt();
        for (Thread t : consumerThreads) t.interrupt();

        // Ждём завершения (с таймаутом)
        for (Thread t : producerThreads) t.join(1000);
        for (Thread t : consumerThreads) t.join(1000);

        // Финальная статистика
        out.println("\n" + "=".repeat(70));
        out.println("ИТОГОВАЯ СТАТИСТИКА:");
        out.println("=".repeat(70));
        out.println("Всего произведено:   " + produced.get());
        out.println("Всего потреблено:    " + consumed.get());
        out.println("Осталось в очереди:  " + queue.size());
        out.println("=".repeat(70));

        // Анализ баланса
        int diff = produced.get() - consumed.get() - queue.size();
        if (diff != 0) {
            out.println("Внимание: расхождение в подсчётах: " + diff);
        } else {
            out.println("Система сбалансирована: все элементы учтены");
        }
    }
}