/*
 * Если необходимо, чтобы при создании объекта производилась какая-то логика, например, чтобы поля класса получали
 * какие-то определенные значения, то можно определить в классе свои конструкторы.
 * При этом если в классе определяются свои конструкторы, то этот класс лишается конструктора по умолчанию.
 */

package OOP;

// class ProgramConstructors {
    
//     public static void main(String[] args) {

//         Person tom = new Person();

//         System.out.println("До изменения");
//         tom.print();

//         System.out.println("После изменения");
//         tom.name = "tim";
//         tom.print();
//     }
// }

// class Person {
    
//     String name;
//     int age;

//     // Конструктор класса
//     Person() {

//         System.out.println("Создание объекта Person");
//         name = "Tom";
//         age = 41;
//     }

//     void print() {
//         System.out.printf("Name: %s; Age: %d\n", name, age);
//     }
// }

// // Еще 1 пример применения конструктора

// class  ProgramConstructors {

//     public static void main(String[] args) {
        
//         Person tom = new Person();
//         Person dom = new Person("Dominik");
//         Person rom = new Person("Romini", 33);

//         tom.print();
//         dom.print();
//         rom.print();


//     }
// }

// class Person {

//     String name;
//     int age;

//     Person() {
//         name = "Неизвестно";
//         age = 18;
//     }

//     Person(String username) {
//         name = username;
//         age = 18;
//     }

//     Person(String username, int userage) {
//         name = username;
//         age = userage;
//     }

//     void print() {
//         System.out.printf("Имя: %s; Возраст: %d\n", name, age);
//     }
// }



/* 
 * Добавляем ключевое слово this - ссылка на текущий объект
*/
// class ProgramConstructors {

//     public static void main(String[] args) {
        
//         Person tom = new Person();
//         Person dom = new Person("Dominik");
//         Person kol = new Person("Kolini", 13);
    
//         tom.print();
//         dom.print();
//         kol.print();
//     }
// }

// class Person {

//     String name;
//     int age;

//     Person() {
//         this("Неизвестно", 18);
//     }

//     Person(String name) {
//         this(name, 18);
//     }

//     Person(String name, int age) {
//         this.name = name;
//         this.age = age;
//     }


//     void print() {
//         System.out.printf("Имя: %s, Возраст: %d\n", name, age);
//     }
// }




/*
 * Инициализация
*/

//  // Пример 1
// class ProgramConstructors {

//     public static void main(String[] args) {
        
//         Person tom = new Person();
//         tom.print();

//         Person gom = new Person("Goji", 45);
//         gom.print();

//     }
// }

// class Person {

//     String name;
//     int age;

//     // Начало инициализации
//     {
//         name = "Неизвестно";
//         age = 18;
//     }
//     // Конец инициализации

//     Person() {} // Пустой конструктор, ни чего не делает

//     Person(String name, int age) {

//         this.name = name;
//         this.age = age;
//     }


//     void print() {
//         System.out.printf("Имя: %s, Возраст: %d\n", name, age);
//     }
// }


// // Пример 2
// class ProgramConstructors {

//     public static void main(String[] args) {
        
//         State state = new State();

//         System.out.println("Final value: " + state.value);
//     }
// }

// class State {

//     String value = "Defualt"; // Значение по умолчанию
    
//     // инициализация
//     {
//         System.out.println("Initializator. Old value: " + value);
//         value = "Initializator";
//     }

//     // Конструктор
//     State() {
//         System.out.println("Constructor. Old value: " + value);
//         value = "Constructor";
//     }
// }


// Пример 3 ловим самое первое значение

class ProgramConstructors {

    public static void main(String[] args) {
        
        State state = new State();

        System.out.println("Final value: " + state.value);
    }
}

class State {
    
    String value = defaultValue();

    {
        System.out.println("Initializator. Old value: " + value);
        value = "Initializator";
    }

    State() {
        System.out.println("Constructor. Old value: " + value);
        value = "Constructor";
    }

    String defaultValue() {
        System.out.println("Default. Old value: " + value);
        return "Defualt";
    }
}