package OOP;

class Program_parametrs {
    public static void main(String[] args) {
        Person tom = new Person();
        Calculator toSum = new Calculator();
        
        tom.says("Hellow world");
        tom.says("Hellow people");

        toSum.sum(15, 15);
        toSum.sum(15, 17);
    }
    
}

class Person {

    void says(String message) {
        System.out.println(message);
    }
}

class Calculator {

    // метод сложения двух чисел
    void sum(int a, int b) {

        int result = a + b;
        System.out.println(result);
    }
}