package Immutable;

import java.util.ArrayList;
import java.util.List;


public class RecordDemo {
    
    public static void main(String[] args) {
        
        // Создаем неизменяемую коллекцию
        List<String> originalTokens = new ArrayList<>();
        
        originalTokens.add("token1");
        originalTokens.add("token2");

        // Создаем неизменяемыый объект
        Record record = new Record(1, "Test", originalTokens);

        // Попытка изменить оригинальную коллекцию не влтяет на объект
        originalTokens.add("token3");
        System.out.println("После изменение исходной коллекции: " + record.getTokens());

        // Попытка изменить полученную коллекцию не влияет на объект
        List<String> returnedTokens = record.getTokens();

        returnedTokens.add("token4");

        System.out.println("После изменения возвращенной коллекции: " + record.getTokens());


        // Правильный способ изменения - создание нового объекта
        Record updatedRecord = record.addToken("token5");
        System.out.println("Оригинальный объект: " + record.getTokens());
        System.out.println("Новый объект: " + updatedRecord.getTokens());
    }
}