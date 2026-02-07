package THREAD.Tasks.WaitingEvents;

import java.util.concurrent.CountDownLatch;

import static java.lang.System.out;
import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

import java.util.Random;

// // Реализация без метода Worker
// public class WorkersTask {
//     public static void main(String[] args) throws InterruptedException {
//         int workerCount = 5;
//         CountDownLatch latch = new CountDownLatch(workerCount);
//         Random random = new Random();

//         out.println("Запуск" + workerCount + " рабочих потоков.\n");

//         // Здесь ваш код для создания и запуска рабочих потоков
//         for (int i = 0; i <= workerCount; i++) {
//             int workerid = i;
//             new Thread(() -> {
//                 try {
//                     int worTime = random.nextInt(5) + 1; // от 1 до 5 сек
//                     out.println("Поток " + workerid + " начал работу на " + worTime + " сек");
                    
//                     sleep(worTime * 1000);

//                     out.println("Поток " + workerid + " завершил работу");
//                 } catch (final InterruptedException InterruptedException) {
//                     currentThread().interrupt();

//                     out.println("Поток " + workerid + " прерван");
//                 } finally {
//                     latch.countDown();  // Уменьшаем четчик
//                 }
//             }).start();
//         }

//         // Ожидание выполнения всех потоков
//         latch.await();
//         out.println("\nВсе работы завершены");
//     }

//     // Здесь реализуйте класс Worker
// }

// Реализация с методом Worker
public class WorkersTask {
    public static void main(String[] args) throws InterruptedException {
        int workerCount = 5;
        CountDownLatch latch = new CountDownLatch(workerCount);

        out.println("Запуск рабочих потоков...");

        // Здесь ваш код для создания и запуска рабочих потоков
        for (int i = 1; i <= workerCount; i++)
            new Thread(new Worker(i, latch)).start();

        // Ожидание выполнения всех потоков
        latch.await();
        out.println("Все работы завершены");
    }

    // Здесь реализуйте класс Worker
    static class Worker implements Runnable {

        private final int id;
        private final CountDownLatch latch;
        private final Random random = new Random();
        
        public Worker(int id, CountDownLatch latch) {
            this.id = id;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                int workTime = random.nextInt(5) + 1; // 1-5 секунд
                out.println("Поток " + id + " начал работу " + workTime + " сек");
                Thread.sleep(workTime * 1000);
                out.println("Поток " + id + " завершил работу");
            } catch (final InterruptedException InterruptedException) {
                currentThread().interrupt();
                out.println("Поток " + id + " прерван");
            } finally {
                latch.countDown();
            }      
        }        
    }
}