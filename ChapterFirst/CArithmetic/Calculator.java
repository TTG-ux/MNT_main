package CArithmetic;

import java.math.*;

public class Calculator
{
    public static void main(String[] args)
    {
        // Обозначаем известные переменные для калькулятора
        // int a = 2;
        // int b = 2;

        // Вывод арифметических выражений
        // System.out.println(a + b);  // 4
        // System.out.println(a - b);  // 0  
        // System.out.println(a * b);  // 4
        // System.out.println(a / b);  // 1
        // System.out.println(a % b);  // 0

        // System.out.println("\n\n\n\n");

        // // Вывод поразрядных операций
        // System.out.println(a & b);      // 2
        // System.out.println(a | b);      // 2  
        // System.out.println(a ^ b);      // 0
        // System.out.println(~ a);        // -3 иневертирует все биты в целые значения
        // System.out.println(a << b);     // 8
        // System.out.println(a >>> b);    // 0
        // System.out.println(a >> b);     // 0

        BigInteger a = BigInteger.valueOf(2147483647);
        BigInteger b = BigInteger.valueOf(2147483647);

        a = a.multiply(b);

        System.out.println(a);
    }    
}
