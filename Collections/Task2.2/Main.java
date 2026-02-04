public class Main {
    public static void main(String[] args) {
        // Создаём список: 1 → 2 → 3
        Node<Integer> list = new Node<>(1,
                new Node<>(2,
                        new Node<>(3, null)
                )
        );

        System.out.print("Исходный: ");
        printList(list); // [1, 2, 3]

        Node<Integer> reversed = NodeUtil.reverse(list);

        System.out.print("Развёрнутый: ");
        printList(reversed); // [3, 2, 1]

        System.out.print("Исходный (не изменился): ");
        printList(list); // [1, 2, 3] — подтверждение иммутабельности
    }

    private static <T> void printList(Node<T> node) {
        StringBuilder sb = new StringBuilder("[");
        while (node != null) {
            sb.append(node.getValue());
            node = node.getNext();
            if (node != null) sb.append(", ");
        }
        sb.append("]");
        System.out.println(sb);
    }
}