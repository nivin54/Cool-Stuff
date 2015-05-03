package session2;

import java.util.Scanner;

/*
 * Java threads need to take care of two problems
 * values getting cached in threads.
 * Synchronization.
 * caching occurs when java tries to optimize. 
 * In java, thread never expects other thread to change is value.
 * For that we can make it volatile, till it don't cache, you can except changes for this variable.  
 */
class Processor extends Thread
{
 
	//private boolean running = true; // This values may get cached and hence it ll be never set to false. 
	private volatile boolean running = true; // This values may get cached and hence it ll be never set to false.
	@Override
	public void run() {	
		// TODO Auto-generated method stub
		while(running)
		{
			System.out.println("Hello World");
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	}
	public void shutdown()
	{
		running = false;
	}
	
	
}
public class App {
	
	public static void main(String[] args) {
	
		Processor processor = new Processor();
		processor.start();
		System.out.println("Press Return to stop");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		processor.shutdown();
	}
	

}
