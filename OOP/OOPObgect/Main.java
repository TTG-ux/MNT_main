package OOP.OOPObgect;

/*
 * Простой пример
 */
// public class Main {
    
//     public static void main(String[] args) {
        
//         // Создание объекта с помощью new
//         Car MyCar = new Car("Toyota", "Camry", 2022);

//         // Использоваение объекта
//         System.out.println(MyCar.brand); // Toyota
//         MyCar.startEngine();
//     }
// }


// class Car {
//     // Состояние (поля)
//     String brand;
//     String model;
//     int year;

//     // Конструктор
//     public Car(String brand, String model, int year) {
//         this.brand = brand;
//         this.model = model;
//         this.year = year;
//     }

//     // Поведение (методы)
//     public void startEngine() {
//         System.out.println("Двигатель запущен");
//     }

//     public void stopEngine() {
//         System.out.println("двигатель остановлен");
//     }
// } 



/*
 * Практический пример
 */
// Использование 
public class Main {

    public static void main(String[] args) {
        Student student = new Student("Ivan");

        student.addGrade(5);
        student.addGrade(4);
        student.addGrade(5);
    
        student.printInfo();
    }
}


class Student {
    // Поля
    private String name;
    private int[] grades;

    // Коструктор
    public Student (String name) {
        this.name = name;
        this.grades = new int[0];
    }

    // Методы
    public void addGrade(int grade) {
        // Создаем новый массив на 1 больше
        int[] newGrades = new int[grades.length + 1];

        // Копируем старые оценки
        for(int i = 0; i < grades.length; i++)
            newGrades[i] = grades[i];
        
        // Добавляем новую оценку
        newGrades[grades.length] = grade;

        // Обновляем массив оценок
        grades = newGrades;
    }


    public double getAverageGrade() {
        
        if (grades.length == 0)
            return 0;
        
        int sum = 0;
        for (int grade: grades)
            sum += grade;
        
        
        return (double) sum / grades.length;
    }


    public void printInfo() {
        System.out.println("Студент: " + name);
        System.out.println("Оценки: ");

        for (int grade : grades)
            System.out.println(grade + " ");

        System.out.println("\nСредний балл: " + getAverageGrade());
    } 
}

