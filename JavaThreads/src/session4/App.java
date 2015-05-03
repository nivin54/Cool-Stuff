package session4;

public class App {

	/*
	 * Each object in a class has an intrinsic lock 
	 * So if you want to have more intrinsic lock,
	 * create a private objects and let them act as an 
	 * lock for the function which you want to synchronize. 
	 * We don't lock or synchronize the list. we prefer to synchronize the methods
	 */
	public static void main(String[] args) {
		Worker worker = new Worker();
		worker.main();
	}
}
