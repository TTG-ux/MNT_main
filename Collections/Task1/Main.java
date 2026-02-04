public class Main {
    public static void main(String[] args) {
        
        MyArrayList list = new MyArrayList();
        
        list.add(10);
        list.add(20);
        list.add(30);
        
        System.out.println(list); // Выведе: [10, 20, 30]
        System.out.println("Размер списка до изменения: " + 
                                                list.size()); // Размер первоначального списка 3
        
        list.remove(1);
        System.out.println("Размер списка после изменения: " + list); // Выведет: [10, 30]
        
        System.out.println("В списке есть элемент 30: " + list.contains(30)); // true
        System.out.println("В списке есть элемент 50: " + list.contains(50)); // false
        System.out.println("Нулевой элемент: " + list.get(0)); // 10
    }
}