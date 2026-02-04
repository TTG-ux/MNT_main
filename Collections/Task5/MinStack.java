package Collections.Task5;

import static java.lang.System.out;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinStack {

    public static void main(String[] args) {
        
        MinStack minStack = new MinStack();

        minStack.push(5);   // [5] 
        minStack.push(100); // [5,10] 
        minStack.push(23);  // [5,10,23] 
        minStack.push(21);  // [5,10,23,21] 
        out.println(minStack.min());    // Выведет мин элемент 5

        minStack.pop();
        out.println(minStack.min());    // Выведет мин элемент 5
        minStack.push(2);
        minStack.pop();
        out.println(minStack.min());    // Выведет мин элемент 5
        minStack.pop();                 // [5,10]
        minStack.pop();                 // [5]
        minStack.push(2);       // [5]
        out.println(minStack.min());    // Выведет мин элемент 2 [5,2]
        minStack.pop();                 // [2]



    }
    
    private final Deque<Integer> stack = new ArrayDeque<>();        // Основной стек
    private final Deque<Integer> minStack = new ArrayDeque<>();     // Стек минимумов 

    // Добавление элементов в стек
    public void push (int element) {
        stack.push(element);

        if (minStack.isEmpty() || element <= minStack.peek())
            minStack.push(element);
    }
    
    // Удаляем и возвращаем верхний элемент стека
    public int pop () {
        if (stack.isEmpty()) throw new IllegalStateException("Stack is empty");

        int val = stack.pop();

        if (val == minStack.peek()) minStack.pop();

        return val;
    }
    
    // Возвращаем текущий минимум
    public int min () {
        if (minStack.isEmpty()) throw new IllegalStateException("Stack is empty");

        return minStack.peek();
    }
}
