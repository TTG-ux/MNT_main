package OOP;

// public class Program {
//     public static void main(String[] args) {
        
//         Person tom = new Person();
//         Person bob = new Person();
        
//         // Устанавливаем новые значения полей объекта tom
//         tom.name = "Tom";
//         tom.age = 41;

//         // Устанавливаем новые значения полей объекта bob
//         bob.name = "Bob";
//         bob.age = 46;

//         // Получаем значения полей объекта tom
//         System.out.print(tom.name + " ");
//         System.out.println(tom.age);

//         // Получаем значения полей объекта bob
//         System.out.print(bob.name + " ");
//         System.out.print(bob.age);
        
//     }    
// }

// class Person {
//     String name;    // Имя
//     int age;        // Возраст
// }

class Program {
    public static void main(String[] args) {
        // Создаём объект Person
        Person tom = new Person();

        // Устанавливаем значения полей класса
        tom.name = "Tom";
        tom.age = 41;

        // Вызываем метод print
        tom.print();

        // Пример с многократным вызовом метода
        tom.name = "Tommy";
        tom.age = 14;
        tom.print();
    }
}

class Person {

    String name;
    int age;

    // Метод print класса Person 
    void print() {
        System.out.printf("Name: %s \tAge: %d\n", name, age);
    }
}   


