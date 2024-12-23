package task2;

public class ThreadSynchronization {
    public static void main(String[] args) throws InterruptedException {
        // Create the shared counter
        Counter counter = new Counter();

        // Create three threads
        IncrementThread thread1 = new IncrementThread(counter);
        IncrementThread thread2 = new IncrementThread(counter);
        IncrementThread thread3 = new IncrementThread(counter);

        // Start all threads
        thread1.start();
        thread2.start();
        thread3.start();

        // Wait for all threads to finish
        thread1.join();
        thread2.join();
        thread3.join();

        // Print the final value of the counter
        System.out.println("Final value of counter: " + counter.getCount());
    }
}