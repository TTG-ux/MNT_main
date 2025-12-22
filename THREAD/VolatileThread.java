package THREAD;

// public class VolatileThread {
    
//     public static void main(String[] args) throws InterruptedException{
          
//         Worker worker = new Worker();
         
//         // Создаем "Поток А" и запускаем его. Он вызовет worker.run()
//         Thread workerThread = new Thread(worker);
//         workerThread.start(); // <-- Здесь запускается run()
 
//         // Даем "Потоку А" немного времени, чтобы он вошел в цикл while(true)
//         Thread.sleep(1000); // Ждем 1 секунду
 
//         // "Поток Б" (главный поток) вызывает stop()
//         worker.stop(); // <-- Здесь вызывается stop()
 
//         // Ждем завершения "Потока А"
//         // Если флаг running не был volatile, join() может ждать вечно!
//         workerThread.join(2000); // Ждем до 2 секунд
 
//         if (workerThread.isAlive()) {
//             System.out.println("--- РЕЗУЛЬТАТ: Поток А все еще работает! ---");
//             System.out.println("Поток А не увидел изменение running = false.");
//             System.exit(1); // Завершаем программу, так как поток "завис"
//         } else {
//             System.out.println("--- РЕЗУЛЬТАТ: Поток А  успешно завершился. ---");
//         }
//     }
// }

// class Worker implements Runnable {
 
//     // Флаг НЕ volatile
//     private boolean running = true;
 
//     @Override
//     public void run() {
         
//         // Этот код выполняется в "Потоке А"
//         System.out.println("Поток А: Начинаю работу...");
//         while (running) {
//             // Здесь может быть некоторая работа
             
//             // JIT-компилятор с высокой вероятностью "закэширует" переменную running
//             // в регистре процессора, так как она не меняется внутри цикла.
//         }
//         // Эта строка может никогда не выполниться
//         System.out.println("Поток А: Работа остановлена.");
//     }
 
//     public void stop() {
//         // Этот код выполняется в "Потоке Б" (главном потоке)
//         System.out.println("Поток Б: Отправляю команду на остановку...");
//         this.running = false;
//     }
// }


// Изменяем на корректный вариант с volatile
public class VolatileThread {
    
    public static void main(String[] args) throws InterruptedException{
          
        Worker worker = new Worker();
         
        // Создаем "Поток А" и запускаем его. Он вызовет worker.run()
        Thread workerThread = new Thread(worker);
        workerThread.start(); // <-- Здесь запускается run()
 
        // Даем "Потоку А" немного времени, чтобы он вошел в цикл while(true)
        Thread.sleep(1000); // Ждем 1 секунду
 
        // "Поток Б" (главный поток) вызывает stop()
        worker.stop(); // <-- Здесь вызывается stop()
 
        // Ждем завершения "Потока А"
        // Если флаг running не был volatile, join() может ждать вечно!
        workerThread.join(2000); // Ждем до 2 секунд
 
        if (workerThread.isAlive()) {
            System.out.println("--- РЕЗУЛЬТАТ: Поток А все еще работает! ---");
            System.out.println("Поток А не увидел изменение running = false.");
            System.exit(1); // Завершаем программу, так как поток "завис"
        } else {
            System.out.println("--- РЕЗУЛЬТАТ: Поток А  успешно завершился. ---");
        }
    }
}

class Worker implements Runnable {
 
    // Флаг volatile
    private volatile boolean running = true;
 
    @Override
    public void run() {
         
        // Этот код выполняется в "Потоке А"
        System.out.println("Поток А: Начинаю работу...");
        while (running) {
            // Здесь может быть некоторая работа
             
            // JIT-компилятор с высокой вероятностью "закэширует" переменную running
            // в регистре процессора, так как она не меняется внутри цикла.
        }
        // Эта строка может никогда не выполниться
        System.out.println("Поток А: Работа остановлена.");
    }
 
    public void stop() {
        // Этот код выполняется в "Потоке Б" (главном потоке)
        System.out.println("Поток Б: Отправляю команду на остановку...");
        this.running = false;
    }
}