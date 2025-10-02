package SWITCHYIELDAndClassicSwitch;

import java.util.Scanner;

public class SwitchClassicOld {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введиите от 2 до 10");

        int day = 1;

        switch (day) {
            case 1:
                System.out.println("Понедельник");
                break;
            case 2:
                System.out.println("Вторник");
                break;
            case 3:
                System.out.println("Среда");
                break;
            case 4:
                System.out.println("Четверг");
                break;
            default:
                System.out.println("Неизвестный день недели");
        }
    }
}
