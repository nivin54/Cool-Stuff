package session8;

import java.util.Scanner;

/*
 * wait and notify can be used only in a synchronized block and not anywhere else.
 * 
 */
public class Processor {
	
	public void produce() throws InterruptedException 
	{
		synchronized (this) {
			System.out.println("Producer thread running ...");
			wait();
			System.out.println("Ressumed");
			
		}
	}
	public void consumer() throws InterruptedException 
	{
		Thread.sleep(500);
		Scanner scanner = new Scanner(System.in);
		synchronized(this){
			
			System.out.println("Waiting for the return key to be pressed. ");
			scanner.nextLine();
			System.out.println("Return key are pressed");
			notify();
			Thread.sleep(500);
		}
	}
	

}
