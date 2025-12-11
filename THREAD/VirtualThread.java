package THREAD;

// // Самый простой способ создать и запустить виртуальный поток представляет
// // статический метод Thread.startVirtualThread(), в который передается объект Runnable:
// public class VirtualThread {
    
//     public static void main(String[] args) {
        
//         System.out.println("Main Thread start...");

//         Runnable task = () -> {

//             System.out.println("Hello from a virtual thread");
//         };

//         Thread.startVirtualThread(task); //Запускаем виртуальный поток

//         System.out.println("Main Thread finished...");
//     }
// //     По подобному консольному выводу не видно, чтобы виртуальный поток выполнялся.
// //     Все потому что, в отличие от платформенных потоков виртуальные потоки по умолчанию определяются как потоки-демоны,
// //     которые выполняются в фоновом режиме. Однако поскольку виртуальные потоки используют тот же
// //     API, что и обычные, мы можем вызвать метод join(), чтобы дождаться завершения виртуального потока:
// }


// //Выводим на консоль сообщение из виртуального потока
// public class VirtualThread{

//     public static void main(String[] args) {
        
//         System.out.println("Main Thread start...");

//         Runnable task = () -> {

//             System.out.println("Hello from a virtual thread");
//         };

//         var t = Thread.startVirtualThread(task); //Запускаем виртуальный поток
        
//         try {
//             t.join(); //Ждём завершение виртуального потока
//         } catch (InterruptedException _) {
//             System.out.println("Main Thread incorrupted");
//         }

//         System.out.println("Main Thread finished...");
//     }
// }



// //Метод ofVirtual()
// public class VirtualThread{

//     public static void main(String[] args) {
        
//         System.out.println("Main Thread start...");

//         Runnable task = () -> {

//             System.out.println("Hello form " + Thread.currentThread().getName());
//         };

//         var t = Thread.ofVirtual()
//                 .name("Mytask") //Устанавлием имя
//                 .start(task);        //Запускаем поток

//         try {
//             t.join(); //Ждём завершение внутреннего потока
//         } catch (InterruptedException _) {
//             System.out.println("Main Thread incorrupted");
//         }

//         System.out.println("Main Thread finished...");
//     }
// }



// //Из прошлого примера можно сначала создать, а потом запустить
// public class VirtualThread{

//     public static void main(String[] args) {
        
//         System.out.println("Main Thread start...");

//         var t = Thread.ofVirtual()
//                 .name("Mytask")
//                 .unstarted(() -> System.out.println("Hello from " + Thread.currentThread().getName()));

//         t.start();

//         try {
//             t.join();
//         } catch (InterruptedException _) {
//             System.out.println("Main Thread interrupted");
//         }

//         System.out.println("Main Thread finished...");
//     }
// }



//Виртуальные потоки имеют фиксированный приоритет потока
public class VirtualThread{

    public static void main(String[] args) {
        
        System.out.println("Main Thread start...");

        Runnable task = () -> {

            Thread t = Thread.currentThread(); //Получаем текущий поток

            System.out.println("Name: " + t.getName()); //Если имя не заданно, то вернется пустая строка
            System.out.println("Is Virtual: " + t.isVirtual());
            System.out.println("Is Daemon: " + t.isDaemon());
            System.out.println("Priority: " + t.getPriority());
            System.out.println("Class: " + t.getClass());
        };

        var thread = Thread.startVirtualThread(task);

        try {
            thread.join();
        } catch (InterruptedException _) {
            System.out.println("Main thread interrupted");
        }
 
        System.out.println("Main thread finished...");
    }
}