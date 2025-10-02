package SWITCHYIELDAndClassicSwitch;

import java.io.FileNotFoundException;
import java.io.UncheckedIOException;

public class SwitchYieldNoution {

    public static void main(String[] args) {
        Exam ternaryBool = Exam.TRUE;

        getYieldQuery(ternaryBool);
    }

    public static void getYieldQuery(Exam ternaryBool) {
        boolean result = switch (ternaryBool){
            //Пример блока, он необходим для использования более одной строки кода в case
            case TRUE -> {
                System.out.println("Bool true");
                yield true;
            }
            case FALSE -> {
                System.out.println("Bool false");
                yield false;
            }
            case FILE_NOT_FOUND -> {
                var ex = new UncheckedIOException("This", new FileNotFoundException());
                throw ex;
            }
            default -> {
                var ex = new IllegalArgumentException("Seriusly");
                    throw ex;
            }    
        };
        System.out.println(result);
    }
}

enum Exam {
    TRUE,
    FALSE,
    FILE_NOT_FOUND
};
