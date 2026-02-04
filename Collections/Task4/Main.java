package Collections.Task4;

import static java.lang.System.out;

import java.util.EmptyStackException;

public class Main {
    
    public static void main(String[] args) {

        // Тестирование связного списка
        LinkedListQueue<Integer> llq = new LinkedListQueue<>();
        llq.enqueue(1);
        llq.enqueue(2);
        llq.enqueue(3);

        out.println(llq.dequeue()); // 1
        out.println(llq.dequeue()); // 2
        out.println(llq.isEmpty()); // false

        // Тестирование массива
        ArrayQueue<String> aq = new ArrayQueue<>();
        aq.enqueue("A");
        aq.enqueue("B");
        aq.enqueue("C");

        out.println(aq.dequeue()); // A
        out.println(aq.dequeue()); // B
        out.println(aq.isEmpty()); // false

        aq.enqueue("D");
        out.println(aq.dequeue()); // C
        out.println(aq.dequeue()); // D
        out.println(aq.isEmpty()); // true
    }
}

// Очередь на основе связного списка
class LinkedListQueue<T> {
    private static class Node<T> {
        T val;
        Node<T> next;
        Node(T val) { this.val = val; }
    }
    
    private Node<T> head, tail;
    private int size;
    
    public void enqueue(T val) {
        Node<T> node = new Node<>(val);
        if (isEmpty()) head = tail = node;
        else tail = tail.next = node;
        size++;
    }
    
    public T dequeue() {
        if (isEmpty()) throw new EmptyStackException();
        T val = head.val;
        head = head.next;
        if (head == null) tail = null;
        size--;
        return val;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
}

// Очередь на основе массива
class ArrayQueue<T> {
    private Object[] arr = new Object[10];
    private int front = 0, size = 0;

    public void enqueue(T val) {
        if (size == arr.length) resize();
        arr[(front + size) % arr.length] = val;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (isEmpty()) throw new EmptyStackException();
        T val = (T) arr[front];
        arr[front] = null;
        front = (front + 1) % arr.length;
        size--;
        return val;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Object[] newArr = new Object[arr.length * 2];
        for (int i = 0; i < size; i++) {
            newArr[i] = arr[(front + i) % arr.length];
        }
        arr = newArr;
        front = 0;
    }
}


    
