package OOP.OOPInheritance;

public class Main {
    public static void main(String[] args) {
        
        Dog dog = new Dog();
        dog.eat();  // Метод из родительского класса
        dog.bark(); // Метод из дочернего класса
    }    
}


class Animal {

    void eat() {
        System.out.println("Animal is eating");
    }
}

class Dog extends Animal {  // С помощью extends класс наследуется от другого класса

    void bark() {
        System.out.println("Dog is barking");
    }
}

