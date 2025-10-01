package SWITCHYIELD;

import java.io.FileNotFoundException;
import java.io.UncheckedIOException;

public class SwitchYieldNoution {

    public static void main(String[] args) {
        // // Classik
        // int day = 2;

        // switch (day) {
        //     case 1:
        //         System.out.println("1");
        //         break;
        //     case 2:
        //         System.out.println("2");
        //         break;

        //     default:
        //         System.out.println("lol");
        // }

        Exam exm = Exam.FILE_NOT_FOUND;

        getYieldQuery(exm);
    }

    public static void getYieldQuery(Exam exm) {
        boolean result = switch (exm){
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
}
