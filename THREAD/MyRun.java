package THREAD;

// // После вызова метода start() поток переходит в состояние RUNNABLE - готовый к запуску.
// // Однако в реальности такой поток еще не выполняется: он может быть запущен, а может и не запущен.
// // Планировщик потоков решает, предоставить ли потоку время для выполнения.
// // После того, как поток запущен, он может периодически приостанавливаться, чтобы дать другим потокам
// // возможность выполниться. Однако точные детали планирования потоков зависят от операционной
// // системы и от природы потока.

// public class MyRun {
    
//     public static void main(String[] args) {
        
//         System.out.println("Main thread start...");

//         Runnable task = ()->{ //Определяем действие потока
//             System.out.println("Runnable start...");

//             System.out.println("Runnable finished...");
//         };

//         var t = new Thread(task); //Определяем поток

//         t.start(); //Запуск потока

//         System.out.println("Main thread finished...");
//     }
// }



// Вместо лямбда-выражения мы можем избрать более долгий путь -
// определить отдельный класс, который реализует интерфейс Runable. Например:
public class MyRun{

    public static void main(String[] args) {
        
        System.out.println("Main thread start...");

        Runnable task = new MyTask();

        var t = new Thread(task); //Опредеялем поток

        t.start(); //Запускаем поток

        System.out.println("Main thread finished...");
    }
}


class MyTask implements Runnable{
    
    public void run(){ //Определяем действия потока

        System.out.println("Runnable start...");
        System.out.println("Runnable finished...");
    }
}