package session1;


/* Two ways of starting threads in java */
/* One is to extend the thread class 
 * 
 * 
 */
class  Runner extends Thread
{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0 ; i < 10 ; i ++ )
		{
			System.out.println("Hello World");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //It is a static method in thread class. 
		}
			
	}
	
}
public class App {

	public static void main(String[] args) {
		Runner runner = new Runner();
		runner.start();
		
	}
}
