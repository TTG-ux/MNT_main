package CConditionalExpressions;

public class ArraysKadane
{
    public static int Erorrnullmas(int[] arr)
    {
        if (arr == null || arr.length == 0) 
        {
            throw new IllegalArgumentException("Массив не должен быть пустым");
        }
    
        int currentSum = arr[0]; // текущая сумма
        int MaxSum = arr[0];      // Максимальная сумма


        for (int i = 1; i < arr.length; i++)
        {
            // Либо начинаем подмассив, либо назначаем новый с arr[i]
            currentSum = Math.max(arr[i], currentSum + arr[i]);

            // Обновляем глобальный максимум
            MaxSum = Math.max(MaxSum, currentSum);
        }

        return MaxSum;
    }


    public static void main(String[] args)
    {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int result = Erorrnullmas(arr);
        
        
        System.out.println(result);
    }
}
