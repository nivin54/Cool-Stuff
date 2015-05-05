package session8;

public class App {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Processor processor = new Processor();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					processor.produce();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// TODO Auto-generated method stub
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					processor.consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
	}
}
