package task3;

import java.util.concurrent.CopyOnWriteArrayList;

//Writer thread that writes values to the list
class WriterThread extends Thread {
private CopyOnWriteArrayList<String> list;

public WriterThread(CopyOnWriteArrayList<String> list) {
   this.list = list;
}

@Override
public void run() {
   // Add new values to the list
   for (int i = 1; i <= 5; i++) {
       String item = "Item " + i;
       list.add(item);
       System.out.println("Added: " + item);
       try {
           Thread.sleep(100); // Simulate some delay in writing
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
   }
}
}
