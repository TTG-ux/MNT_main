package THREAD;

import java.util.stream.IntStream;

// // Не добовляем sleep и join
// public class SleepAndJoin {
    
//     private static final int FROM_NUMBER_FIRST_THREAD = 1;
//     private static final int TO_NUMBER_FIRST_THREAD = 500;

//     private static final int FROM_NUMBER_SECOND_THREAD = 501;
//     private static final int TO_NUMBER_SECOND_THREAD = 1000;

//     private static final String TEMPLATE_MESSAGE_THREAD_NAME_AND_NUMBER = "%s : %d\n";

//     public static void main(String... args){
        
//         final TaskSummingNumbers firstTask = startSubTask(FROM_NUMBER_FIRST_THREAD, TO_NUMBER_FIRST_THREAD);
//         final TaskSummingNumbers secondTask = startSubTask(FROM_NUMBER_SECOND_THREAD, TO_NUMBER_SECOND_THREAD);

//         final long resultNumber = firstTask.getResultNumber() + secondTask.getResultNumber();
//         printThreadNameAndNumber(resultNumber);
//     }

//     private static void printThreadNameAndNumber(final long number){
//         System.out.printf(TEMPLATE_MESSAGE_THREAD_NAME_AND_NUMBER, Thread.currentThread().getName(), number);
//     }

//     private static TaskSummingNumbers startSubTask(final int fromNumber, final int toNumber){
//         final TaskSummingNumbers subtask = new TaskSummingNumbers(fromNumber, toNumber);
//         final Thread thread = new Thread(subtask);
//         thread.start();
//         return subtask;
//     }

//     private static final class TaskSummingNumbers implements Runnable{
//         //Значаение которое будет лежать в resultNumber после вызова консруктора 
//         private static final int INITIAL_VALUE_RESULT_NUMBER = 0;

//         private final int fromNumber;
//         private final int toNumber;
//         private long resultNumber;

//         public TaskSummingNumbers(final int fromNumber, final int toNumber){
//             this.fromNumber = fromNumber;
//             this.toNumber = toNumber;
//             this.resultNumber = INITIAL_VALUE_RESULT_NUMBER;
//         }

//         public long getResultNumber() {
//             return this.resultNumber;
//         }

//         @Override
//         public void run() {
//             IntStream.rangeClosed(this.fromNumber, this.toNumber).forEach(i -> this.resultNumber += i);
//             printThreadNameAndNumber(this.resultNumber);
//         }
//     }
// }


// //Используем sleep
// public class SleepAndJoin {
    
//     private static final int FROM_NUMBER_FIRST_THREAD = 1;
//     private static final int TO_NUMBER_FIRST_THREAD = 500;

//     private static final int FROM_NUMBER_SECOND_THREAD = 501;
//     private static final int TO_NUMBER_SECOND_THREAD = 1000;

//     private static final String TEMPLATE_MESSAGE_THREAD_NAME_AND_NUMBER = "%s : %d\n";

//     private static final int TIME_WAITING_IN_MILLTS = 1000;

//     public static void main(String... args) throws InterruptedException{
        
//         final TaskSummingNumbers firstTask = startSubTask(FROM_NUMBER_FIRST_THREAD, TO_NUMBER_FIRST_THREAD);
//         final TaskSummingNumbers secondTask = startSubTask(FROM_NUMBER_SECOND_THREAD, TO_NUMBER_SECOND_THREAD);
//         waitForTasksFinished();

//         final long resultNumber = firstTask.getResultNumber() + secondTask.getResultNumber();
//         printThreadNameAndNumber(resultNumber);
//     }

//     private static void printThreadNameAndNumber(final long number){
//         System.out.printf(TEMPLATE_MESSAGE_THREAD_NAME_AND_NUMBER, Thread.currentThread().getName(), number);
//     }

//     private static TaskSummingNumbers startSubTask(final int fromNumber, final int toNumber){
//         final TaskSummingNumbers subtask = new TaskSummingNumbers(fromNumber, toNumber);
//         final Thread thread = new Thread(subtask);
//         thread.start();
//         return subtask;
//     }

//     //Логика засыпания потока
//     private static void waitForTasksFinished() throws InterruptedException{
//         Thread.sleep(TIME_WAITING_IN_MILLTS);
//     }


//     private static final class TaskSummingNumbers implements Runnable{
//         //Значаение которое будет лежать в resultNumber после вызова консруктора 
//         private static final int INITIAL_VALUE_RESULT_NUMBER = 0;

//         private final int fromNumber;
//         private final int toNumber;
//         private long resultNumber;

//         public TaskSummingNumbers(final int fromNumber, final int toNumber){
//             this.fromNumber = fromNumber;
//             this.toNumber = toNumber;
//             this.resultNumber = INITIAL_VALUE_RESULT_NUMBER;
//         }

//         public long getResultNumber() {
//             return this.resultNumber;
//         }

//         @Override
//         public void run() {
//             IntStream.rangeClosed(this.fromNumber, this.toNumber).forEach(i -> this.resultNumber += i);
//             printThreadNameAndNumber(this.resultNumber);
//         }
//     }
// }



//Используем join
public class SleepAndJoin {
    
    private static final int FROM_NUMBER_FIRST_THREAD = 1;
    private static final int TO_NUMBER_FIRST_THREAD = 500;

    private static final int FROM_NUMBER_SECOND_THREAD = 501;
    private static final int TO_NUMBER_SECOND_THREAD = 1000;

    private static final String TEMPLATE_MESSAGE_THREAD_NAME_AND_NUMBER = "%s : %d\n";

    public static void main(String... args) throws InterruptedException{
        
        final TaskSummingNumbers firstTask = new TaskSummingNumbers(FROM_NUMBER_FIRST_THREAD, TO_NUMBER_FIRST_THREAD);
        final Thread firstThread = new Thread(firstTask);
        firstThread.start();

        final TaskSummingNumbers secondTask = new TaskSummingNumbers(FROM_NUMBER_SECOND_THREAD, TO_NUMBER_SECOND_THREAD);
        final Thread secondThread = new Thread(secondTask);
        secondThread.start();
        //Ожиаем завершения потоков
        waitForTasksFinished(firstThread, secondThread);

        final long resultNumber = firstTask.getResultNumber() + secondTask.getResultNumber();
        printThreadNameAndNumber(resultNumber);
    }

    private static void printThreadNameAndNumber(final long number){
        System.out.printf(TEMPLATE_MESSAGE_THREAD_NAME_AND_NUMBER, Thread.currentThread().getName(), number);
    }

    //Логика засыпания потока
    private static void waitForTasksFinished(final Thread... threads) throws InterruptedException{
        for(final Thread thread : threads){
            thread.join();
        }
    }


    private static final class TaskSummingNumbers implements Runnable{
        //Значаение которое будет лежать в resultNumber после вызова консруктора 
        private static final int INITIAL_VALUE_RESULT_NUMBER = 0;

        private final int fromNumber;
        private final int toNumber;
        private long resultNumber;

        public TaskSummingNumbers(final int fromNumber, final int toNumber){
            this.fromNumber = fromNumber;
            this.toNumber = toNumber;
            this.resultNumber = INITIAL_VALUE_RESULT_NUMBER;
        }

        public long getResultNumber() {
            return this.resultNumber;
        }

        @Override
        public void run() {
            IntStream.rangeClosed(this.fromNumber, this.toNumber).forEach(i -> this.resultNumber += i);
            printThreadNameAndNumber(this.resultNumber);
        }
    }
}