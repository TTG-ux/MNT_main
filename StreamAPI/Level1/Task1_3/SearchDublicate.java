package StreamAPI.Level1.Task1_3;

import static java.lang.System.out;

import java.util.stream.IntStream;

public class SearchDublicate {
    
    public static void main(String[] args) {
        
        int[] numbers = {5, 3, 5, 2, 8, 2, 5, 8};

        // Считаем уникальные числа через StreamAPI
        long Unique = IntStream.of(numbers) // Создает поток примитимов int из массива, он эффективнее чем Stream<Integer>
                               .distinct()  // фильтрует поток, оставляя только уникальные элементы
                               .count();    // возвращает кол-во элементов в потоке как long

        out.println("Кол-во уникальных чисел: " + Unique);
    }
}
