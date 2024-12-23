package task3;

import java.util.concurrent.CopyOnWriteArrayList;


public class ConcurrentDataStructures {
	public static void main(String[] args) throws InterruptedException {
	     // Create the CopyOnWriteArrayList (shared data structure)
	 CopyOnWriteArrayList<String> sharedList = new CopyOnWriteArrayList<>();
	
	 // Create multiple reader and writer threads
	 Thread reader1 = new ReaderThread(sharedList);
	 Thread reader2 = new ReaderThread(sharedList);
	 Thread writer1 = new WriterThread(sharedList);
	
	 // Start the threads
	 writer1.start();
	 reader1.start();
	 reader2.start();
	
	 // Wait for all threads to finish
	 writer1.join();
	 reader1.join();
	 reader2.join();
	
	 // Final state of the shared list
	 System.out.println("Final list: " + sharedList);
	}
}