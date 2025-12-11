package THREAD;

// // Другой способ создания потока представляет наследование от класса Thread. Например:

// public class ThreadCreate {
    
//     public static void main(String[] args) {
        
//         System.out.println("Main thread start...");

//         var t = new MyThread(); // Определяем поток

//         t.start(); //Запускаем поток

//         System.out.println("Main thread finished...");
//     }

// }

// class MyThread extends Thread{

//     MyThread(){
//         super(); //Вызываем конструктор базового класса
//     }

//     public void run(){

//         System.out.println("MyThread start...");
//         System.out.println("MyThread finished...");
//     }
// }


// // Аналогично созданию одного потока мы можем запускать сразу несколько потоков:

// public class ThreadCreate{

//     public static void main(String[] args) {
        
//         System.out.println("Main thread start...");

//         for(int i=0; i < 10; i++)
//         {
//             var task = new MyTask("MyTask_" + i);
//             new Thread(task).start();
//         }

//         System.out.println("Main thread finished...");
//     }
// }

// class MyTask implements Runnable{

//     private String name;

//     MyTask(String name) {this.name = name;}

//     public void run(){ //Определяем действие потока

//         System.out.println(name + "started...");
//         System.out.println(name + "finished...");
//     }
// }



// Получаем имена текущих потоков
class MyTask implements Runnable {
 
    public void run(){  // определяем действия потока
          
        // получаем имя текущего потока
        String name = Thread.currentThread().getName();
        System.out.println(name + " started...");
        System.out.println(name + " finished...");
    }
} 
 
public class ThreadCreate {
   
    public static void main(String[] args) {
           
        System.out.println("Main thread started...");
 
        for(int i=1; i < 100; i++){
 
            var task = new MyTask();
            new Thread(task).start();
        }
 
        System.out.println("Main thread finished...");
    }
}