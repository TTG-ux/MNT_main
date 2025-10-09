package OOP;

public class ProgramMethodOverloading {
 
    public static void main(String[] args) {

        Calculator calc = new Calculator();

        System.out.println(calc.sum(1, 2));
        System.out.println(calc.sum(1.1, 2.2));
        System.out.println(calc.sum(1, 2, 4));
    }
}

class Calculator {

    int sum(int x, int y) {
        return x + y;
    }

    double sum(double x, double y) {
        return x + y;
    }

    int sum(int x, int y, int z) {
        return x + y + z;
    }

}