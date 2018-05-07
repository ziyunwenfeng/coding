package thread;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Source source = new Source();
		new Thread(new Cousumer(source)).start();
		new Thread(new Producer(source)).start();
		new Thread(new Cousumer(source)).start();
		new Thread(new Producer(source)).start();
		
	}

}
