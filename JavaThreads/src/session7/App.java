package session7;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {
	
	private BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10); //It is thread safe.
	public static void main(String[] args) {
		
	}
	private void producer() throws InterruptedException
	{
		Random random = new Random();
		while (true)
		{
			queue.put(random.nextInt(100));			
		}
	}
	private void consumer() throws InterruptedException
	{
		Random random = new Random();
		while(true)
		{
			Thread.sleep(100);
			if(random.nextInt(10) == 0)
			{
				Integer value = queue.take();
				System.out.println("Taken value " + value + "  Queue size is " + queue.size() );
				
			}
			
		}
	}

}
