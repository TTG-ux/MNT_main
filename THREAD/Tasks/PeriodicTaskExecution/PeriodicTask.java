package THREAD.Tasks.PeriodicTaskExecution;

import java.util.concurrent.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PeriodicTask {

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        long[] interval = {2}; // массив для захвата в лямбде (как мутабельная переменная)
        ScheduledFuture<?>[] task = new ScheduledFuture[1];

        // Здесь ваш код для периодического выполнения задачи
        Runnable job = () -> 
            System.out.println("Время: " + LocalDateTime.now().format(formatter) + 
                              " | Период: " + interval[0] + " сек");
        
        task[0] = scheduler.scheduleAtFixedRate(job, 0, interval[0], TimeUnit.SECONDS);

        System.out.println("Запущено\n");
        System.out.println("Для остановки нажмите клавишу Enter\n");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                break; // остановка по нажатию Enter
            }            
        }

        scheduler.shutdown();
        System.out.println("Планировщик остановлен");
        scanner.close();
    }
}