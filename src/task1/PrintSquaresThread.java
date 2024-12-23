package task1;

public class PrintSquaresThread extends Thread {
	@Override
	public void run() {
		for (int i=0; i<=10; i++) {
			System.out.println("Square of " + i + ": " + (i * i));
		}
	}

}
