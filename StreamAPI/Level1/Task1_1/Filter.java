package StreamAPI.Level1.Task1_1;

import static java.lang.System.out;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Filter {
    
    public static void main(String[] args) {
        
        List<Integer> numbers = Arrays.asList(3, 6, 9, 12, 15, 18);

        // Фильтрация четных цифр через StreamApi
        List<Integer> filter = numbers.stream()
                                      .filter(n -> n % 2 == 0)
                                      .collect(Collectors.toList());

        out.println(filter);
    }
}
