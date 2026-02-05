package StreamAPI.Level1.Task1_2;

import static java.lang.System.out;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Upper {
    
    public static void main(String[] args) {
        
        String[] words = {"java", "stream", "api"}; // Создается поток из массива строк

        // Конвертируем в верхний регист через StreamAPI
        String WORDS = Arrays.stream(words)
                             .map(String::toUpperCase) // Преобразуем каждое слово в верхний регистр
                             .collect(Collectors.joining("\n")); // Объеденяем элементы в одну строку с новой строки
        
        out.println(WORDS);
    }
}