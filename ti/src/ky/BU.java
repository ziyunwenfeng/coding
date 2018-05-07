package ky;
class A{
	public synchronized void test(){
		System.out.println("start:"+Thread.currentThread().getName());
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("end:"+Thread.currentThread().getName());
	}
}
public class BU {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		A a = new A();
		for(int i=0;i<5;i++){
			new Thread(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					a.test();
				}
				
			}).start();
		}
		
		synchronized(a){
			a.notify();
		}
		System.out.println("::::::::::::::::::::::::");
		synchronized(a){
			a.notifyAll();
		}
	}

}
