package StreamAPI.Level4.Task4_3;

import static java.lang.System.out;

import java.util.stream.IntStream;

public class InnfiniteThreads {
    
    public static void main(String[] args) {
        
        // Создаем с помощью StreamAPI бесконечный поток четных первых 10 чисел
        int sum = IntStream.iterate(2, n -> n + 2)
                           .limit(10)
                           .sum();
                        

        out.println("Первые 10 четных чисел: 2,4,6,8,10,12,14,16,18,20");
        out.println("Сумма первый 10и четных чисел: " + sum);
    }
}