package OOP;

// class Program_parametrs {
//     public static void main(String[] args) {
//         Person tom = new Person();
//         Calculator toSum = new Calculator();
        
//         tom.says("Hellow world");
//         tom.says("Hellow people");

//         toSum.sum(15, 15);
//         toSum.sum(15, 17);
//     }
    
// }

// class Person {

//     void says(String message) {
//         System.out.println(message);
//     }
// }

// class Calculator {

//     // метод сложения двух чисел
//     void sum(int a, int b) {

//         int result = a + b;
//         System.out.println(result);
//     }
// }


// /*
//  * Параметры переменной длины
//  * Метод может принимать параметры переменной длины одного типа.
//  * Например, нам надо передать в метод набор числел и вычислить их сумму,
//  * но мы точно не знаем, сколько именно чисел будет передано - 3, 4, 5 или больше. 
//  */
// class Program_parametrs {
//     public static void main(String[] args) {
        
//         Calculator calc = new Calculator();

//         calc.sum(3, 3, 3, 3, 3);
//         calc.sum(3, 3, 3, 3);
//         calc.sum(3, 3, 3);
//         calc.sum(3, 3);
//     }
// }

// class Calculator {
//     /*
//      * Троеточие перед названием параметра int ...nums указывает на то, что он
//      * будет необязательным и будет представлять массив.
//      * Мы можем передать в метод sum одно число, несколько чисел, а можем вообще не передавать никаких параметров.
//      */
//     void sum(int ...nums) {

//         int result = 0;
//         for (int n: nums) {
//             result += n;
//         }
//         System.out.println(result);
//     }
// }


/*
 * Если мы передаём несколько параметров, то необязательный передаём в конце
 */

 class Program_parametrs {

    public static void main(String[] args) {
        
        Calculator calc = new Calculator();

        calc.sum("Sum 1,2,3 - ", 1,2,3);
        calc.sum("Sum zero - ");
    }
 }

 class Calculator {
    
    void sum(String message, int ...nums) {

        System.out.print(message);

        int result = 0;
        for(int i: nums) {
            result += i;
        }
        System.out.println( result);
    }
 }