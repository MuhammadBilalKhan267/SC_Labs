package task1;

public class MultithreadingExample {
    public static void main(String[] args) {
        // Create instances of the threads
        PrintNumbersThread thread1 = new PrintNumbersThread();
        PrintSquaresThread thread2 = new PrintSquaresThread();

        // Start both threads
        thread1.start();
        thread2.start();
        
        try {
            // Ensure both threads complete before the program exits
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Both threads have finished executing.");
    }
}