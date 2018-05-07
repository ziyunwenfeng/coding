package thread;

public class Thread1Thread2Test {

	public static void main(String[] args) {
		Storage storage = new Storage();
		// TODO Auto-generated method stub
		Thread1 thread11 = new Thread1(storage);
		Thread1 thread12 = new Thread1(storage);
		Thread1 thread13 = new Thread1(storage);
		Thread1 thread14 = new Thread1(storage);
		Thread1 thread15 = new Thread1(storage);
		Thread1 thread16 = new Thread1(storage);
		
//		Thread thread21 = new Thread(new Thread2(),"thread21");
//		Thread thread22 = new Thread(new Thread2(),"thread22");
//		Thread thread23 = new Thread(new Thread2(),"thread23");
//		Thread thread24 = new Thread(new Thread2(),"thread24");
		Thread thread25 = new Thread(new Thread2(),"thread25");
		
		Thread2 thread21 = new Thread2(storage);
		Thread2 thread22 = new Thread2(storage);
		Thread2 thread23 = new Thread2(storage);
		thread11.setNum(2);
		thread12.setNum(1);
		thread13.setNum(2);
		thread14.setNum(5);
		thread15.setNum(7);
		thread16.setNum(3);
		
		thread21.setNum(1);
		thread22.setNum(4);
		thread23.setNum(6);
		
		thread11.start();
		thread12.start();
		thread13.start();
		thread14.start();
		thread15.start();
		thread16.start();
		
		thread21.start();
		thread22.start();
		thread23.start();
		

		
	}

}
