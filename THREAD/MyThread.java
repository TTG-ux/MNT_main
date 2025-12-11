package THREAD;

// public class MyThread {
    
//     public static void main(String[] args) {
        
//         //Получаем текущий поток
//         Thread t = Thread.currentThread();
//         System.out.println(t); // Thread[#3,main,5,main]
//         /*
//          * Идентификатор потока - 3
//          * имя потока - main
//          * приоритет потока - 5
//          * группа потока - main
//          */
//     }
// }


// С помошью статического метода Thread.getAllStackTrace() мы можем получить трассировку стека
// для всех активных потоков в программе в виде словаря Map<Thread, StackTraceElement[]>

public class MyThread {

    public static void main(String[] args) {
        
        //Получаем только поток без трасировки стека
        var threads = Thread.    getAllStackTraces().keySet();
        
        for(var thread: threads)
            System.out.println(thread);
    }
}