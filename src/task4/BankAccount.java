package task4;

import java.util.concurrent.atomic.AtomicInteger;

//BankAccount class to simulate the bank account
class BankAccount {
 private AtomicInteger balance; // Using AtomicInteger for thread-safe operations

 public BankAccount(int initialBalance) {
     this.balance = new AtomicInteger(initialBalance);
 }

 // Deposit method (atomic operation)
 public synchronized void deposit(int amount) {
     if (amount > 0) {
         balance.addAndGet(amount);
         System.out.println("Deposited: " + amount + ", New Balance: " + balance.get());
     }
 }

 // Withdrawal method (atomic operation)
 public synchronized void withdraw(int amount) {
     if (amount > 0 && balance.get() >= amount) {
         balance.addAndGet(-amount);
         System.out.println("Withdrew: " + amount + ", New Balance: " + balance.get());
     } else {
         System.out.println("Insufficient funds to withdraw: " + amount);
     }
 }

 // Get the current balance
 public int getBalance() {
     return balance.get();
 }
}