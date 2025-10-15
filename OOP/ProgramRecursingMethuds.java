package OOP;

public class ProgramRecursingMethuds {
    
    public static void main(String[] args) {
        
        int fuctorial4 = fuctorial(4);

        System.out.printf("Факториал числа 4 = %d\n", fuctorial4);
    }

    static int fuctorial(int n) {

        if (n == 1) return 1;

        return n * fuctorial(n - 1);
    }
}
