package task4;

import java.util.Random;

//Client thread simulates clients performing random deposit and withdrawal operations
class ClientThread extends Thread {
 private BankAccount account;
 private Random random = new Random();

 public ClientThread(BankAccount account) {
     this.account = account;
 }

 @Override
 public void run() {
     for (int i = 0; i < 10; i++) { // Perform 10 random transactions
         int operation = random.nextInt(2); // 0 for deposit, 1 for withdrawal
         int amount = random.nextInt(1000) + 1; // Random amount between 1 and 200

         if (operation == 0) {
             account.deposit(amount); // Deposit
         } else {
             account.withdraw(amount); // Withdraw
         }

         try {
             Thread.sleep(100); // Simulate some delay between operations
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
     }
 }
}

