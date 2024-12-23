package task3;

import java.util.concurrent.CopyOnWriteArrayList;

//Reader thread that reads values from the list
class ReaderThread extends Thread {
private CopyOnWriteArrayList<String> list;

public ReaderThread(CopyOnWriteArrayList<String> list) {
   this.list = list;
}

@Override
public void run() {
   // Read all elements in the list
   for (String item : list) {
       System.out.println("Read: " + item);
       try {
           Thread.sleep(50); // Simulate some delay in reading
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
   }
}
}