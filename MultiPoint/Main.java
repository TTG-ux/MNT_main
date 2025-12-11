package MultiPoint;

// public class Main {

//     public static void main(String[] args) {
//         MyThread thread = new MyThread();

//         System.out.println("Вызываем run() на прямую:");
//         thread.run();   // Выполняется в main потоке

//         System.out.println("Вызвваем start() на прямую");
//         thread.start();
//     }
    
// }


// class MyThread extends Thread {
//     @Override
//     public void run() {
//         System.out.println("run() выполняется в потоке: " + Thread.currentThread().getName());
//     }
// }


/*
 * Иммплементация интерфейса Runnable
 */


public class Main {

    public static void main(String[] args) {
        
        System.out.println("Creating a new thread from: " + Thread.currentThread());
        
        var MyRunnable = new MyRunnable();

        var thread = new Thread(MyRunnable);

        thread.start();

        System.out.println("Leaving from: " + Thread.currentThread());
    }
}

class MyRunnable implements Runnable {

    @Override
    public void run() {

        System.out.println("Using Runnable from: " + Thread.currentThread());
        System.out.println("Hellow world");
    }
}