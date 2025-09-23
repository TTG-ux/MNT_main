package BDataType;

public class DataTypesDemo
{
    public static void main(String[] args) {
        
        // int a = 1;
        // double b = 1.1;
        // char c = 3; //U+0001
        // boolean d = true;
        // byte e = 1;
        // short f = 1;
        // long g = 1;
        // float h = 3;

        // // int a = null


        // System.out.println(a + "\n" + b + "\n" + c + "\n" + d + "\n" + e + "\n" + f + "\n" + g +"\n"+ h);


        // String str1 = "Hello";
        // String str2 = str1;
        // str1 = "World";
        // Integer a_1 = null;

        // System.out.println("str2 = " + str2);
        // System.out.println("str1 = " + str1 + a_1);




        // ================================
        // 1. Приведение типов (явное и неявное)
        // ================================

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("=== 1. Приведение типов ===");

        // Неявное приведение (расширение — widening): byte -> int -> long -> float -> double
        byte b_1 = 100;
        int i = b_1;            // byte -> int (без потерь)
        long l = i;             // int -> long
        float f_1 = l;          // long -> float (может быть потеря точности)
        double d_1 = f_1;       // float -> double

        System.out.println("byte -> int -> long -> float -> double: " + d_1);

        // Явное приведение (сужение — narrowing): double -> int -> byte
        double bigNumber = 12345.6789;
        int fromDouble = (int) bigNumber;   // отбрасывается дробная часть -> 12345
        byte fromInt = (byte) fromDouble;   // 12345 -> переполнение byte (-128..127) -> 57

        System.out.println("double -> int: " + fromDouble);     // 12345
        System.out.println("int -> byte: " + fromInt);          // 57 (12345 % 256 = 57)

        // Опасное приведение: потеря данных
        int huge = 300;
        byte small = (byte) huge; // 300 -> 300 - 256 = 44
        System.out.println("300 как byte: " + small); // Вывод: 44



        // ================================
        // 2. Упаковка и распаковка (Boxing / Unboxing)
        // ================================

        System.out.println("\n=== 2. Упаковка и распаковка ===");

        // Упаковка (boxing): примитив -> объект
        int primitiveInt = 42;
        Integer boxedInt = Integer.valueOf(primitiveInt); // Явная упаковка
        // или просто:
        Integer autoBoxed = 42; // Автоматическая упаковка (autoboxing)

        // Распаковка (unboxing): объект -> примитив
        int unboxed = boxedInt.intValue(); // Явная распаковка
        // или просто:
        int autoUnboxed = autoBoxed;       // Автоматическая распаковка

        System.out.println("Упаковано: " + boxedInt);
        System.out.println("Распаковано: " + unboxed);

        // ⚠️ Опасность: NullPointerException при распаковке null
        Integer nullBox = null;
        try {
            int dangerous = nullBox; // ← NullPointerException!
        } catch (NullPointerException e_1) {
            System.out.println("Ошибка: попытка распаковать null -> " + e_1.getMessage());
        }

        // ================================
        // 3. Переполнение целых чисел (Integer Overflow)
        // ================================

        System.out.println("\n=== 3. Переполнение целых чисел ===");

        // Переполнение int
        int maxInt = Integer.MAX_VALUE; // 2_147_483_647
        System.out.println("Максимальное int: " + maxInt);
        int overflowInt = maxInt + 1;   // Переполнение -> переходит в отрицательное
        System.out.println("После переполнения: " + overflowInt); // -2_147_483_648

        // Переполнение byte
        byte maxByte = 127;
        byte overflowByte = (byte)(maxByte + 1); // 128 -> -128
        System.out.println("byte 127 + 1 = " + overflowByte); // -128

        // Переполнение short
        short maxShort = 32767;
        short overflowShort = (short)(maxShort + 1); // -> -32768
        System.out.println("short 32767 + 1 = " + overflowShort);

        // Переполнение long
        long maxLong = Long.MAX_VALUE;
        long overflowLong = maxLong + 1;
        System.out.println("long MAX + 1 = " + overflowLong); // переходит в отрицательное
        System.out.println("long MAX = " + maxLong);

        // 🔍 Как избежать переполнения? Используйте Math.addExact() (выбрасывает исключение)
        try {
            int safeAdd = Math.addExact(maxInt, 1);
        } catch (ArithmeticException e_1) {
            System.out.println("Переполнение обнаружено: " + e_1.getMessage());
        }

        // 🧮 Дополнительно: умножение с переполнением
        int large = 1_000_000;
        int result = large * large; // 10^12, но int вмещает только ~2*10^9 -> переполнение
        System.out.println("1_000_000 * 1_000_000 = " + result); // Отрицательное число!

        // Безопасная версия:
        try {
            int safeMult = Math.multiplyExact(large, large);
        } catch (ArithmeticException e_1) {
            System.out.println("Переполнение при умножении: " + e_1.getMessage());
        }	    
    }
}
