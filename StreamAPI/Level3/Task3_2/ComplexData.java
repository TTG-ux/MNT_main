package StreamAPI.Level3.Task3_2;

import static java.lang.System.out;

import java.util.Arrays;
import java.util.List;

public class ComplexData {
    
    public static void main(String[] args) {
        
        List<String> data = Arrays.asList("1,2,3", "4,5,6", "7,8,9");

        // Преобразуем, объеденяем и суммируем при помощи StreamAPI
        int result = data.stream()
                          // Преобразуем каждый элемент в поток и сортируем в один поток
                         .flatMap(s -> Arrays.stream(s.split(",")))    
                         .mapToInt(Integer::parseInt)   // эффективно преобразуем строки в примитивы
                         .sum();    // агрегируем значения за один проход

        out.println("Сумма всех чисел: " + result);
    }
}
