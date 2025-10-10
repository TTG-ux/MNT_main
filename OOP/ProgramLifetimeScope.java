package OOP;

class ProgramLifetimeScope {
    
    public static void main(String[] args) {
        
        Person.printCount(); // Count 0

        Person bob = new Person("Bob");

        {
            Person tom = new Person("Tom");
            tom.printName(); // Name: Tom
        } // Здесь формально завершается время жизни объекта tom и его переменная name
        // чуть позже сборщик мусора удалит переменную tom и ее переменную name

        bob.printName();    // Name: Bob


        Person.printCount(); // Count 2
    } // Здесь формально завершается время жизни объекта bob и его переменной name
} // здесь завершается время жизни статической переменной count

class Person {
    
    String name;            // Время жизни - время жизни объекта
    static int count = 0;   // Время жизни - время жизни программы

    public Person(String name) {
        this.name = name;
        count++;            // Для теста увеличиваем счетчик объектов
    }

    public void printName() {
        System.out.println("Name: " + name);
    }

    public static void printCount() {
        System.out.println("Count: " + count);
    }
}
