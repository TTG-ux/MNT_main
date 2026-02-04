public final class Node<T> {
    private final T value;
    private final Node<T> next;

    Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public Node<T> getNext() {
        return next;
    }
}

abstract class NodeUtil {

    private NodeUtil() {
    }

    public static <T> Node<T> reverse(Node<T> node) {
        Node<T> reversed = null;
        Node<T> current = node;

        // Проходим по исходному списку и строим новый в обратном порядке
        while (current != null) {
            // Каждый новый узел становится головой развернутого списка
            reversed = new Node<>(current.getValue(), reversed);
            current = current.getNext();
        }

        return reversed;
    }
}