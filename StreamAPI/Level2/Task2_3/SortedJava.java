package StreamAPI.Level2.Task2_3;

import static java.lang.System.out;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SortedJava {
    
    public static void main(String[] args) {
        
        String[] words = {"java", "stream", "example", "simple", "javascript"};

        // Фильтруем строки с помощью StreamAPI
        List<String> filteredWords = Arrays.stream(words)   // Создаем поток из массива строк
                                           // Фильтруем строки и оставляем только не что начинаются с java
                                           .filter(s -> s.startsWith("java"))
                                           .collect(Collectors.toList());   // Собираем отфильтрованные строки в один список

        out.println(filteredWords);
    }
}