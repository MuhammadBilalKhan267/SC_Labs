package task2;

class Counter {
    private int count = 0;

    // Synchronized method to increment the counter
    public synchronized void increment() {
        count++;
    }

    // Method to get the current value of the counter
    public int getCount() {
        return count;
    }
}