package StreamAPI.structure;

import static java.lang.System.out;

// Структура лямбда-выражения
// public class TextProcessor {
//     public static void main(String[] args) {
//         TextFormatter formatter;
//         formatter = (text) -> text.toUpperCase();

//         String result = formatter.format("hello web developer");
//         System.out.println(result); // HELLO WEB DEVELOPER
//     }
// }

interface TextFormatter {
    String format(String text);    
}


// Сравнение с традиционным подходом
public class TextProcessor {
    public static void main(String[] args) {
        TextFormatter formatter = new TextFormatter() {
            @Override
            public String format(String text) {
                return text.toUpperCase();
            }
        };

        String result = formatter.format("hello web developer");
        System.out.println(result);
    }
}