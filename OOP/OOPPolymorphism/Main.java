package OOP.OOPPolymorphism;

public class Main {
    
    public static void main(String[] args) {
        
        Animal cat = new Cat();
        Animal dog = new Dog();

        cat.sound();
        dog.sound();
    }
}


class Animal {

    void sound() {
        System.out.println("Animal make a sound");
    }
}

class Dog extends Animal {
    
    void sound() {
        System.out.println("Dog says bark");
    }
}

class Cat extends Animal {

    void sound() {
        System.out.println("Cat says meow");
    }
}