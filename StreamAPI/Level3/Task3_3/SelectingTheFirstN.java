package StreamAPI.Level3.Task3_3;

import static java.lang.System.out;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SelectingTheFirstN {
    
    public static void main(String[] args) {
        
        int[] numbers = {7, 3, 5, 7, 9, 3, 1, 8, 9, 2};

        // Фильтруем и удаляем дубликаты и выводим первые 3 элемента с помощью StreamAPI
        List<Integer> result = Arrays.stream(numbers)
                                     // Удаляем дубликаты, сохраняя порядок первого появления элемента
                                     .distinct()            
                                     .filter(n -> n > 4)    // оставляем числа большие 4
                                     .limit(3)     // из отфильрованныз берем первые 3 числа
                                     .boxed()              // преобразуем IntStream в List<Integer>
                                     .collect(Collectors.toList());

        out.println(result);
    }
}