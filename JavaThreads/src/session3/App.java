package session3;
/*
 * Thread can run parallel
 * So to synchroize we use join method. 
 * It join with the parent thread.
 * incrementing count is not an atomic operation. 
 * increment count is having total of 3 steps. 
 * Threads interleaving.
 * intrinsic lock, monitor.
 * If multiple threads. 
 */


public class App {

	private int count = 0;

	public synchronized void increment()
	{
		count += 1;
	}
	public static void main(String[] args) {
		App app = new App();
		app.dowork();
	}

	public void dowork() {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 10000; i++) {
					increment();
				}

			}
		});
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 10000; i++) {
					increment();
				}

			}
		});
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("What is the value " + count );
	}
}
