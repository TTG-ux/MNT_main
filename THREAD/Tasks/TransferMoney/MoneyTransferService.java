package THREAD.Tasks.TransferMoney;

import java.math.BigDecimal;

public class MoneyTransferService {
    
    // Пример с переводом отрицательной суммы или 0
    public static boolean transferMoneyMinusOrZero(Account from, Account to, BigDecimal amount) {

        if (from == null || to == null || amount == null )
            return false;

        if (amount.compareTo(BigDecimal.ZERO) <= 0)
            return false;

        if (from == to)
            return false;   // Перевод самому себе невозможен

        // Блокировка потоков счетов
        Account first = from.getId() < to.getId() ? from : to;
        Account second = from.getId() < to.getId() ? to : from;

        synchronized (first) {
            synchronized (second) {
                if (from.withdraw(amount)) {
                    to.deposit(amount);
                    return true;
                }
                return false;
            }
        }
    }

    // Стандартный положительный перевод (CAS)
    public static boolean transfer(Account from, Account to, BigDecimal amount) {
        if (from == null || to == null || amount == null)
            return false;

        if (amount.compareTo(BigDecimal.ZERO) <= 0)
            return false;

        if (from == to)
            return false;

        int maxAttempts = 100;
        int attempts = 0;

        while (attempts < maxAttempts) {
            attempts++;

            // Считываем текущие балансы
            BigDecimal fromBalance = from.getBalance();
            BigDecimal toBalance = to.getBalance();

             // Проверяем достаточность средств
            if (fromBalance.compareTo(amount) < 0) {
                return false;
            }
            
            // Вычисляем новые балансы
            BigDecimal newFromBalance = fromBalance.subtract(amount);
            BigDecimal newToBalance = toBalance.add(amount);

            // Проверяем положителен ли счет
            if (from.compareAndSet(fromBalance, toBalance)) {

                while (attempts < maxAttempts) {
                    attempts++;
                    if (to.compareAndSet(toBalance, fromBalance))
                        return true;

                    toBalance = to.getBalance();
                    newToBalance = toBalance.add(amount);
                }

                // Если не удалось обновить получателя -> открываем отправителя
                from.compareAndSet(newFromBalance, fromBalance);
                return false; 
            }            
        }

        return false;
    }
}
