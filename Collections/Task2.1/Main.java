public class Main {
    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        
        // Добавление элементов
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        
        System.out.print("Список: ");
        list.printList(); // [10, 20, 30, 40]
        
        // Проверка наличия элемента
        System.out.println("Содержит 30? " + list.contains(30)); // true
        System.out.println("Содержит 99? " + list.contains(99)); // false
        
        // Удаление элемента
        boolean removed = list.remove(20);
        System.out.println("Удален 20? " + removed); // true
        
        System.out.print("После удаления 20: ");
        list.printList(); // [10, 30, 40]
        
        // Удаление головы
        list.remove(10);
        System.out.print("После удаления головы: ");
        list.printList(); // [30, 40]
        
        // Удаление хвоста
        list.remove(40);
        System.out.print("После удаления хвоста: ");
        list.printList(); // [30]
        
        // Попытка удалить несуществующий элемент
        boolean notRemoved = list.remove(999);
        System.out.println("Удален 999? " + notRemoved); // false
    }
}