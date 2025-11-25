package Test;

public class MainTest {
    
    public static void main(String[] args) {

        // int[] arr = new int[3];

        // arr[0] = 1;
        // arr[1] = 2;
        // arr[2] = 3;

        // for(int i = 0; i < arr.length; i++) {

        //     System.out.println(arr[i]);
        // }

        // Tank t1 = new Tank();
        // Tank t2 = new Tank();

        // t1.level = 9;
        // t2.level = 47;
        // System.out.println("1: t1.level: " + t1.level + ", t2.level: " + t2.level);

        // t1 = t2;
        // System.out.println("2");

    //     boolean b = test1(0) && test2(1) && test3(2);
    //     System.out.println("выражение: " + b);

    // }

    // static boolean test1(int val) {
    //     System.out.println("test1(" + val + ")");
    //     System.out.println("результат: " + (val < 1));
    //     return val < 1;
    // }

    // static boolean test2(int val) {
    //     System.out.println("test1(" + val + ")");
    //     System.out.println("результат: " + (val < 2));
    //     return val < 2;
    // }

    // static boolean test3(int val) {
    //     System.out.println("test1(" + val + ")");
    //     System.out.println("результат: " + (val < 3));
    //     return val < 3;
    // }

    Person per = new Person();
    System.out.println();
    }
}


// class Tank {
//     int level;
// }

class Person {

    private String name;
    private int age;

    @Override
    public String toString() {
        return "Person{name='}" + name + "', age =" + age + "}";
    }

}
