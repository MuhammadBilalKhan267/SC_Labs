package task2;

//Thread class to increment the counter 100 times
class IncrementThread extends Thread {
 private Counter counter;

 // Constructor to pass the shared counter object
 public IncrementThread(Counter counter) {
     this.counter = counter;
 }

 // Thread's run method
 @Override
 public void run() {
     for (int i = 0; i < 100; i++) {
         counter.increment();
     }
 }
}