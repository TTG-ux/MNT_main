package StreamAPI.Level2.Task2_1;

import static java.lang.System.out;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Sorted {
    
    public static void main(String[] args) {
        
        List<String> fruits = Arrays.asList("груша", "яблоко", "банан", "апельсин");

        // Сортируем в алфавитном порядке через StreamAPI
        List<String> sorted = fruits.stream()   // Создает поток из списка строк
                                    .sorted()   // сортирует элементы в естественном порядке (лексикографически)
                                    .collect(Collectors.toList()); // Компануем список

        out.println(sorted);
    }
}