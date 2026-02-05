package StreamAPI.structure;

import static java.lang.System.out;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.function.Supplier;

public class SimpeExample {
    
    public static void main(String[] args) {
        
        // Создаем Supplier который генерирует случайное число от 1 до 100
        Supplier<Integer> randomNumber = () -> new Random().nextInt(100) + 1;

        // Используем его
        out.println("Случайное число: " + randomNumber.get());
        out.println("Еще случайное число: " + randomNumber.get());

        // Suplier который возвращает текущее время
        Supplier<String> currentTime = () -> LocalDateTime.now().toString();

        out.println("Текущее время: " + currentTime.get());

        // Suplier который генерирует случайный пароль
        Supplier<String> passwordgen = () -> {
            String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            StringBuilder password = new StringBuilder();

            Random random = new Random();

            for (int i = 0; i < 15; i++)
                password.append(chars.charAt(random.nextInt(chars.length())));

            return password.toString();
        };

        out.println("Новый пароль: " + passwordgen.get());
    }
}
