package thread;

public class Test123455 {
	static int count = 0;
	class Add extends Thread{
		public void run(){
			add();
		}
	}
	class Dec extends Thread{
		public void run(){
			dec();
		}
	}
	public static synchronized void add(){
		for(int i=0;i<10;i++)
			count++;
	}
	public static synchronized void dec(){
		for(int i=0;i<10;i++)
			count--;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test123455 t = new Test123455();
		Add t1 = t.new Add();
		Dec t2 = t.new Dec();
		t1.start();
		t2.start();
	//	System.out.println("mmmmmm"+count);
		
		if((!t1.isAlive())&&(!t2.isAlive()))
			System.out.println("end"+count);
		else
			System.out.println("isalive");
		if(Thread.activeCount()==1){
			System.out.println("end"+count);
		}
		else
			System.out.println("isalive------");
	}

}
