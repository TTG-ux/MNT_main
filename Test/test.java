import java.io.FileNotFoundException;
import java.io.UncheckedIOException;

public class test
{
    public static void main(String[] args)
    {
        boolean result = switch (ternaryBool) {
        case TRUE -> true;
        case FALSE -> false;
        case FILE_NOT_FOUND -> throw new UncheckedIOException(
            "This is ridiculous!",
            new FileNotFoundException());
        default -> throw new IllegalArgumentException("Seriously?!");
        };
    }
}