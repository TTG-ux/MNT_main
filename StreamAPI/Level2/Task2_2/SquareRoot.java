package StreamAPI.Level2.Task2_2;

import static java.lang.System.out;

import java.util.Arrays;

public class SquareRoot {
    
    public static void main(String[] args) {
        
        int[] numbers = {3, 7, 10, 15, 20, 25, 30};

        // Создаем фильтр через StreamAPI
        int sum = Arrays.stream(numbers)
                        .filter(n -> n % 5 == 0)    // Отфильтруем те что делятся на 5
                        .map(n -> n * n)            // Отфильтрованные числа возводим в квадрат
                        .sum();                     // Суммируем

        out.println("Сумма квадратов: " + sum);
    }
}