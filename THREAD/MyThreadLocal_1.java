package THREAD;

// public class MyThreadLocal_1 {

//     //Создаём ThreadLocal переменную
//     private static ThreadLocal<Integer> threadLocalValue = ThreadLocal.withInitial(() -> 0);
    

//     public static void main(String[] args) {
        
//         //Создаём 2 потока
//         Thread thread1 = new Thread(() -> {
            
//             threadLocalValue.set(10);
//             System.out.println("Thread 1 value = " + threadLocalValue.get());
//         });
        
//         Thread thread2 = new Thread(() -> {
            
//             threadLocalValue.set(20);
//             System.out.println("Thread 2 value = " + threadLocalValue.get());
//         });

//         thread1.start(); //Запускаем первый поток
//         thread2.start(); //Запускаем второй поток

//     }
// }



// пример, в котором мы будем использовать ThreadLocal
// для хранения уникального идентификатора пользователя в каждом потоке.

public class MyThreadLocal_1{

    //Создаём ThreadLocal переменную для хранения идентификатора пользователя
    private static ThreadLocal<Integer> userId = ThreadLocal.withInitial(() -> null);

    public static void main(String[] args) {
        
        //Создаём и запускаем несколько потоков
        Thread thread1 = new Thread(() -> {

            userId.set(1); //Устанавлиев значение для потока 1
            System.out.println("Thread 1 User ID: " + userId.get());
        });

        Thread thread2 = new Thread(() -> {

            userId.set(2); //Устанавлиев значение для потока 2
            System.out.println("Thread 2 User ID: " + userId.get());
        });


        thread1.start();
        thread2.start();


        //Ждём завершение потока
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Печатаем значение в основном потоке (будет null, если не установленно)
        System.out.println("Main Thread User ID: " + userId.get()); // Будет null
    }
}