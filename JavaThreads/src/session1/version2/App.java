package session1.version2;

public class App {
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("I am here to see what is happening.");
				
			}
		});
		t1.start();
	}

}
