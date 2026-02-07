package THREAD.Tasks.TransferMoney;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

public class Account {
    
    private final long id;
    private final AtomicReference<BigDecimal> balance;

    public Account(long id, BigDecimal initialbalance) {
        if (initialbalance.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("баланс не может быть отрицательным");
        
        this.id = id;
        this.balance = new AtomicReference<>(initialbalance);
    }

    public long getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance.get();
    }

    public synchronized boolean withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }

        BigDecimal current = balance.get();
        if (current.compareTo(amount) >= 0) {
            balance.set(current.subtract(amount));
            return true;
        }

        return false;
    }

    public synchronized void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Депозит не может быть отрицательным или 0");
        }

        balance.set(balance.get().add(amount));
    }

    // CAS
    public boolean compareAndSet(BigDecimal expected, BigDecimal updated) {
        return balance.compareAndSet(expected, updated);
    }

    @Override
    public String toString() {
        return "Account [id = " + id + ", balance = " + balance.get() + "]";
    }
}
