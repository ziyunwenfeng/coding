package ky;

public class XV {
	public synchronized void method1(){
		System.out.println("method1 start");
		
		try {
			System.out.println("method1 execute");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("method1 end");
	}
	
	public synchronized void method2(){
		System.out.println("method2 start");
		
		try {
			System.out.println("method2 execute");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("method2 end");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		XV test = new XV();
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				test.method1();
			}
			
		}).start();
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				test.method2();
			}
			
		}).start();
	}

}
