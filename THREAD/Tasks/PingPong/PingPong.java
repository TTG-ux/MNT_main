package THREAD.Tasks.PingPong;

import static java.lang.System.out;

public class PingPong {
    
    private final int iterations;
    private boolean pingTurn = true;
    private int count = 0;

    public PingPong(int iterations) {
        this.iterations = iterations;
    }

    // Метод для потока "ping"
    public synchronized void ping() throws InterruptedException {
        while (count < 2 * iterations) {
            // Поток ждет своей очереди или завершается
            while (!pingTurn && count < 2 * iterations) {
                wait();
            }

            // Проверяем на завершение после пробуждения
            if (count >= 2 * iterations)
                break;

            out.println("ping");
            
            pingTurn = false;
            count++;
            notifyAll(); // Пробуждаем поток "pong"
        }
    }

    // Метод для "pong"
    public synchronized void pong() throws InterruptedException {
        while (count < 2 * iterations) {
            // Поток ждет своей очереди или завершается
            while ( pingTurn && count < 2 * iterations) {
                wait();
            }

            // Проверяем на завершение после пробуждения
            if (count >= 2 * iterations)
                break;

            out.println("pong");
            
            pingTurn = true;
            count++;
            notifyAll(); // Пробуждаем поток "ping"
        }
    }

    public static void main(String[] args) throws InterruptedException {
        
        int N = 10; // Кол-во итераци, к примеру 10

        PingPong test = new PingPong(N);

        // Поток "ping" начинает первым
        Thread pingThread = new Thread(() -> {
            for (int i = 0; i < N; i++) {
                try {
                    test.ping();
                } catch (InterruptedException InterruptedException) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Поток Ping");

        // Поток "pong"
        Thread pongThread = new Thread(() -> {
            for (int i = 0; i < N; i++) {
                try {
                    test.pong();
                } catch (InterruptedException InterruptedException) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Поток Pong");

        // Тесты
        pingThread.start();
        pongThread.start();

        // Ожидаем завершения потоков
        pingThread.join(); // join вызывает ' InterruptedException' его нужно словить вначале "public static void main"
        pongThread.join();

        out.println("\nКонец");
    }
}