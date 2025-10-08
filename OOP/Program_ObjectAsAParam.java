package OOP;

class Program_ObjectAsAParam {

    public static void main(String[] args) {
        
        Person bob = new Person();
        bob.age = 22;
        System.out.printf("bob.age in main (befor twice): %d\n", bob.age);

        Calculator calc = new Calculator();
        calc.twice(bob);

        System.out.printf("bob.age in main (after twice): %d\n",bob.age);
    }    
}

class Person {

    int age;
}

class Calculator {

    void twice(Person p) {
        
        p.age = p.age + p.age;
        System.out.printf("p.age in twice: %d\n", p.age);
    }
}
