package Methods.MethodsOverloading;

public class Main {
    
    public static void main(String[] args) {
    
        OverloadingExample sum1 = new OverloadingExample();

        System.out.println(sum1.sum(1, 2));;
        System.out.println(sum1.sum(1.1, 2.2));
        System.out.println(sum1.sum(1, 2, 4));
        System.out.println(sum1.add(1, 2, 3, 4, 5, 6));
        
    }
}

class OverloadingExample {
    // Перегруженные методы sum
    public int sum(int a, int b) {
        return a + b;
    }

    public double sum(double a, double b) {
        return a + b;
    }

    public int sum(int a, int b, int c) {
        return a + b + c;
    }

    // Пример с varargs
    public int add(int... numbers) {
        int sum = 0;
        for(int num : numbers)
            sum += num;
        
        return sum; 
    }
}
