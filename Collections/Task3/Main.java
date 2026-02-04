import static java.lang.System.out;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Main {
    
    public static boolean isBalanced(String s) {
        
        if (s == null || s.isEmpty()) {
            return false; // Считаем пустую строку сбалансированной
        }

        Map<Character, Character> bracketPairs = new HashMap<>();
        bracketPairs.put(')', '(');
        bracketPairs.put('}', '{');
        bracketPairs.put(']', '[');

        Deque<Character> stack = new ArrayDeque<>();

        for (char ch : s.toCharArray()) {
            // Если скобка открывающая, добавляем её в стек
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            }
            // если скобка закрывающая, тогда проверяем соответствие
            else if (ch == ')' || ch == '}' || ch == ']') {
                // Если стек пуст или верхний элемент не соответствует текущей закрывающей скобке
                if (stack.isEmpty()){
                    return false; // Несбалансировано
                }

                // Проверяем соответствие верхнего элемента стека
                char expectedOpen = bracketPairs.get(ch);
                char actualOpening = stack.pop();

                if(actualOpening != expectedOpen){
                    return false; // Несбалансировано
                }
            }

            // Игнорируем все остальные символы
        }

        // Стек должен быть пустым, если все скобки сбалансированы
        return stack.isEmpty();
    }


    /*
    Проверяем
    */

    public static void main(String[] args) {
        String test1 = "{[()]}"; // Сбалансировано
        String test2 = "{[(])}"; // Несбалансировано
        String test3 = "{{[[(())]]}}"; // Сбалансировано
        String test4 = "((()))"; // Сбалансировано
        String test5 = "(()"; // Несбалансировано
        String test6 = "())"; // Несбалансировано
        String test7 = ""; // Считаем несбалансированной
        String test8 = null; // Считаем несбалансированной

        out.println(isBalanced(test1)); // true
        out.println(isBalanced(test2)); // false
        out.println(isBalanced(test3)); // true
        out.println(isBalanced(test4)); // true
        out.println(isBalanced(test5)); // false
        out.println(isBalanced(test6)); // false
        out.println(isBalanced(test7)); // false
        out.println(isBalanced(test8)); // false
    }
}
