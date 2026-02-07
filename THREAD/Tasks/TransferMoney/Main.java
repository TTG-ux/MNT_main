package THREAD.Tasks.TransferMoney;

import static java.lang.System.out;

import java.math.BigDecimal;

public class Main {
    
    public static void main(String[] args) {
        
        Account user1 = new Account(1, new BigDecimal(100000.00));
        Account user2 = new Account(2, new BigDecimal(500000.00));

        out.println("==== Балансы ====");
        out.println(user1);
        out.println(user2);

        // Неуспешный перевод
        out.println("== Неудачный перевод 100000 с 1 счета на 2 == ");

        boolean result1 = MoneyTransferService.transferMoneyMinusOrZero(user1, user2, new BigDecimal(100000.00));

        out.println("Перевод выполнен: " + result1);

        out.println(user1);
        out.println(user2);

        // Обычный перевод
        out.println("\n=== Оптимистичный перевод 150 с счета 2 на счет 1 ===");
        boolean result2 = MoneyTransferService.transfer(user2, user1, new BigDecimal(150.00));
        out.println("Перевод выполнен: " + result2);
        out.println(user1);
        out.println(user2);
        
        // Попытка перевода с недостаточным балансом
        out.println("\n=== Попытка перевода 2000000 (недостаточно средств) ===");
        boolean result3 = MoneyTransferService.transferMoneyMinusOrZero(user1, user2, new BigDecimal(2000000.00));
        out.println("Перевод выполнен: " + result3);
        out.println(user1);
        out.println(user2);
    }
}
