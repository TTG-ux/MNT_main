package Inheritance;

public class Main {
    
    public static void main(String[] args) {
        

        // // Upcasting - Восходящее преобразование
        // Animal dog2 = new Dog();

        // Dog dog = new Dog();

        // dog.eat();
        // dog.bark();
        // dog.gav();
        // dog.por();
        // // Upcasting - Восходящее преобразование
        // Animal animal = dog; // Автоматическое восходящее преобразование

        // animal.gav();
        // animal.por();
        // animal.eat();


        // // Downcasting - нисходящее преобразование
        // Animal animal2 = new Dog();
        // Dog dog3 = (Dog) animal;


        Animal animal = new Dog(); // Upcasting
        animal.makesound();
        // animal.fetch();  // Ошибка компиляции

        if (animal instanceof Dog dog) {
            dog.fetch();    // Переменная dog уже доступна
        }


        Dog dog = (Dog) animal;    // Downcasting
        dog.fetch();
        dog.makesound();
    }
}
