package Methods.ParameterPassingExample;

public class ParameterPassingExample {
    public void main() {
        // Примитивный тип
        int x = 10;
        modifyPrimitive(x);
        System.out.println("Примитив после метода: " + x); // Останется 10

        // Массив (ссылочный тип)
        int[] arr = {1, 2, 3};
        modifyArray(arr);
        System.out.println("Массив после метода: " + arr[0]); // Станет 100

        // StringBuilder (ссылочный тип)
        StringBuilder sb = new StringBuilder("Привет");
        modifyStringBuilder(sb);
        System.out.println("StringBuilder после метода: " + sb); // Станет "Привет, мир!"

        // String (особый случай - иммутабельный)
        String str = "Привет";
        modifyString(str);
        System.out.println("String после метода: " + str); // Останется "Привет"
    }

    private void modifyPrimitive(int value) {
        value = 20; // Изменение не повлияет на оригинал
    }

    private void modifyArray(int[] arr) {
        arr[0] = 100; // Изменение повлияет на оригинал
    }

    private void modifyStringBuilder(StringBuilder sb) {
        sb.append(", мир!"); // Изменение повлияет на оригинал
    }

    private void modifyString(String str) {
        str = str + ", мир!"; // Изменение не повлияет на оригинал (создается новый объект)
    }
}