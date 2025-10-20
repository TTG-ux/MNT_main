package Interfaces;

// class Main {
    
//     public static void main(String[] args) {
//         Movable car = new Car();
//         Movable aircraft = new Aircraft();

//         car.move();
//         aircraft.move();
//     }
// }
// // Основной класс движения
// interface Movable {
    
//     void move();
// }

// // Класс машины
// class Car implements Movable {

//     public void move() {
//         System.out.println("Машина едет");
//     }
// }

// class Aircraft implements Movable {

//     public void move() {
//         System.out.println("Самолёт летит");
//     }
// }


// /*
//  * Метод по умолчанию
//  */

// class Main {

//     public static void main(String[] args) {
//         Movable car = new Car();

//         car.move();
//         car.stop();
//     }
// }

// interface Movable {

//     void move();

//     default void stop() {
//         System.out.println("Остановка");
//     }
// }

// class Car implements Movable {

//     public void move() {
//         System.out.println("Машина едет");
//     }

//     // // Можно переопределить метод stop
//     // public void stop() {
//     //     System.out.println("Машина останавливается");
//     // }
// }

/*
 * Private методы
 * Подобные методы могут использоваться только внутри самого интерфейса, в котором они определены.
 * То есть к примеру нам надо выполнять в интерфейсе некоторые повторяющиеся действия, и в
 * этом случае такие действия можно выделить в приватные методы:
 */

class Main {

    public static void main(String[] args) {
        
        Sumable c = new Operation();

        System.out.println(c.sum(1, 2));
        System.out.println(c.sum(1, 2, 4));
    }
}

interface Sumable {

    default int sum(int a, int b) {
        return sumALL(a, b);
    }

    default int sum(int a, int b, int c) {
        return sumALL(a, b, c);
    }

    private int sumALL(int... values) {

        int result = 0;
        
        for(int n : values)
            result += n;
        
        return result;
    }
}

class Operation implements Sumable {}