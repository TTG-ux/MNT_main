import static java.lang.System.out;

public class MyLinkedList {

    // Внутренний класс списка
    private static  class Node{

        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Ссылка на первый элемент списка
    private Node head;

    // Ссылка на последний элемент списка
    private Node tail;

    // Текущее кол-во элементов
    private int size;


    // Консруктор
    public MyLinkedList() {

        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Метод для добавления элемента в конец списка
    public void add(int value) {

        Node newNode = new Node(value);

        if(head == null) {
            // Список пуст - новый элемент становится и головой, и хвостом
            head = newNode;
            tail = newNode;
        } else {
            // Добавляем в конец и обновляем tail
            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }

    // Метод для удаления первого встреченного элемента со значением value.
    // Возвращаем true, если элемент был удален, и false в противном случае.
    public boolean remove(int value) {

        if(head == null) {
            // Список пуст
            return false;
        }

        if(head.data == value) {
            // Удаляем головной
            head = head.next;

            if(head == null) {
                // Если список стал пустым, обновляем tail
                tail = null;
            }

            size--;
            return true;
        }

        // Поиск элемента для удаления
        Node current = head;

        while(current.next != null) {
            if(current.next.data == value) {
                // Удаляем элемент
                current.next = current.next.next;

                if(current.next == null) {
                    // Если удален был последний элемент, обновляем tail
                    tail = current;
                }

                size--;
                return true;
            }
            current = current.next;
        }

        // Элемент не найден, выводим false
        return false;
    }

    // Метод, который возвращает true, если список содержит элемент со значением value,
    // в противном случае выводит false.
    public boolean contains(int value) {

        Node current = head;

        while(current != null) {
            if(current.data == value) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    // Метод, который выводит все элементы списка на консоль.
    public void printList() {

        if(head == null) {
            out.println("Список пуст: []");
            return;
        }

        StringBuilder sb = new StringBuilder("[");
        Node current = head;

        while(current != null) {
            sb.append(current.data);
            if(current.next != null)
                sb.append(", ");

            current = current.next;
        }

        sb.append("]");
        out.println(sb.toString());
    }
}
