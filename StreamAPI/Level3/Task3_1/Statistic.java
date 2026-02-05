package StreamAPI.Level3.Task3_1;

import static java.lang.System.out;

import java.util.Arrays;
import java.util.IntSummaryStatistics;

public class Statistic {
    
    public static void main(String[] args) {
        
        int[] numbers = {10, 15, 5, 30, 25, 45};

        // Используем StreamAPI с одним подходом для всех метриков
        IntSummaryStatistics stats = Arrays.stream(numbers)
                                           .summaryStatistics();    // Собираем статистику за один проход

        out.println("Min: " + stats.getMin());
        out.println("Max: " + stats.getMax());
        out.println("Aver: " + stats.getAverage());


        /*
        Не по заданию
        */

        out.println(stats.getCount() + "\n" + stats.getClass() + "\n" + stats.getSum());
    }
}