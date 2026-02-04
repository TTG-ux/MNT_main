package Collections.SideQuest;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class PriorityQueue {

    public static void main(String[] args) {
        
        PriorityQueue test = new PriorityQueue();

        test.enqueue(4, "Low");
        test.enqueue(3, "High");
        test.enqueue(2, "Medium");
        test.enqueue(1, "Crirical");

        out.println(test.dequeue());    // Critical (приоритет 1)
        out.println(test.dequeue());    // High (приоритет 1)
        out.println(test.peek());       // Medium (приоритет 2)
        out.println(test.dequeue());    // Medium 
        out.println(test.dequeue());    // Low
        out.println(test.isEmpty());    // true
        
    }

    
    // Элемент очереди: приоритет + значение
    private static class Node {
        
        int priority;
        String value;

        Node (int priority, String value) {
            this.priority = priority;
            this.value = value;
        }
    }

    private final ArrayList<Node> heap = new ArrayList<>();

    // добавляет элемент в очередь с указанным приоритетом
    public void enqueue(int priority, String value) {
        Node node = new Node(priority, value);

        heap.add(node);

        siftUp(heap.size() - 1);
    }

    // удаляет и возвращает элемент с наивысшим приоритетом. Если очередь пуста,
    // необходимо выбросить исключение NoSuchElementException
    public String dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty");

        Node min = heap.get(0);
        Node last = heap.remove(heap.size() - 1);

        if(!heap.isEmpty()) {
            heap.set(0, last);
            siftDowm(0);
        }

        return min.value;
    }

    // возвращает элемент с наивысшим приоритетом, не удаляя его из очереди.\
    // Также может выбрасывать NoSuchElementException, если очередь пуста.
    public String peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        return heap.get(0).value;
    }

    // возвращает true, если очередь пуста, и false в противном случае.
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    // возвращает количество элементов в очереди.
    public int size() {
        return heap.size();
    }


    /*
    Вспомогательные методы для работы с кучей
    */

    private void siftUp(int idx) {
        while (idx > 0) {
            int parent = (idx - 1) / 2;

            if (heap.get(idx).priority >= heap.get(parent).priority) 
                break;

            swap(idx, parent);
            idx = parent;
        }
    }

    private void swap(int i, int j) {
        Node tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
    }

    private void siftDowm(int idx) {
        int n = heap.size();
        
        while (true) {
            int left = 2 * idx + 1;
            int right = 2 * idx + 2;
            int smallest = idx;

            if (left < n && heap.get(left).priority < heap.get(smallest).priority)
                smallest = left;
            
            if (right < n && heap.get(right).priority < heap.get(smallest).priority)
                smallest = right;

            if (smallest == idx)
                break;

            swap(idx, smallest);
            idx = smallest;
        }
    }

    /*
    Вспомогательные методы для работы с кучей
    */


}
