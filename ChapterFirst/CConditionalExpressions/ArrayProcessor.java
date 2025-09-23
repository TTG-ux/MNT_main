package CConditionalExpressions;

public class ArrayProcessor {

    public static int findMaxSubarraySum(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Массив не должен быть пустым");
        }

        int currentSum = arr[0];  // текущая сумма подмассива
        int maxSum = arr[0];      // максимальная сумма на данный момент

        for (int i = 1; i < arr.length; i++) {
            
            // Либо продолжаем подмассив, либо начинаем новый с arr[i]
            currentSum = Math.max(arr[i], currentSum + arr[i]);
            // Обновляем глобальный максимум
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    // Тест
    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int result = findMaxSubarraySum(arr);
        System.out.println("Максимальная сумма подмассива: " + result); // -> 6
    }
}
