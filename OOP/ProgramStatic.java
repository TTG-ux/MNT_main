package OOP;

// class ProgramStatic {

//     public static void main(String[] args) {
        
//         Person tom = new Person(41);
//         tom.checkAge(); // Сколько лет осталось до пенсии


//         Person bob = new Person(68);
//         bob.checkAge(); // Уже на пенсии


//         // Получение статического поля
//         System.out.println(Person.retirementAge); // 65

//         // Изменение статического поля
//         Person.retirementAge = 67;
//         System.out.println(Person.retirementAge); // 67
//     }
// }

// class Person {

//     int age; // Обычное поле
//     static int retirementAge = 65; // Статическое поле

//     Person(int age) {
//         this.age = age;
//     }

//     void checkAge() {
//         if(age >= retirementAge)
//             System.out.println("Уже на пенсии");
//         else
//             System.out.printf("Сколько лет осталось до пенсии: %d\n", retirementAge - age);
//     }
// }


// /*
//  * Статические инициализаторы
//  */
// class ProgramConstructors {
//     public static void main(String[] args) {
        
//         Person tom = new Person(41);
//         Person ger = new Person(68);

//         tom.checkAge();
//         ger.checkAge();
//     }
// }

// class Person {
//     int age;
//     static int retirementAge;

//     static {
//         retirementAge = 65;
//         System.out.println("Static initializator");
//     }

//     Person(int age) {
//         this.age = age;
//         System.out.println("Constructor");
//     }

    
//     void checkAge() {
//         if (age >= retirementAge)
//             System.out.println("Уже на пенсии");
//         else
//             System.out.printf("Сколько лет осталось до пенсии: %d\n", retirementAge - age);
//     }
// }



/*
 * Обращение к статическим методам
 */

class ProgramConstructors {

    public static void main(String[] args) {

        Person tom = new Person(41);

        Person.checkStatus(tom);
    }
}

class Person {

    int age;
    static int retirementAge = 65;

    Person(int age) {
        this.age = age;
    }

    // Статический метод
    static void checkStatus(Person person) {
        if(person.age >= retirementAge)
            System.out.println("Уже на пенсии");
        else
            System.out.printf("До пенсии осталось: %d лет\n", retirementAge - person.age);
    }
}