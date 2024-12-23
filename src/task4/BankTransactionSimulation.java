package task4;

public class BankTransactionSimulation {
    public static void main(String[] args) throws InterruptedException {
        // Create a bank account with an initial balance of 1000
        BankAccount account = new BankAccount(1000);

        // Create multiple client threads (for example, 5 clients)
        Thread client1 = new ClientThread(account);
        Thread client2 = new ClientThread(account);
        Thread client3 = new ClientThread(account);
        Thread client4 = new ClientThread(account);
        Thread client5 = new ClientThread(account);

        // Start all client threads
        client1.start();
        client2.start();
        client3.start();
        client4.start();
        client5.start();

        // Wait for all threads to finish
        client1.join();
        client2.join();
        client3.join();
        client4.join();
        client5.join();

        // Print the final balance
        System.out.println("Final account balance: " + account.getBalance());
    }
}