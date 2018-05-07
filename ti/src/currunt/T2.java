package currunt;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class T2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadPoolExecutor pool = new ThreadPoolExecutor(4,6,3,
				TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(3),
				new ThreadPoolExecutor.DiscardOldestPolicy());
		for(int i=0;i<10;i++){
			pool.execute(new Task(i));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}
class Task implements Runnable{
	private Object data;
	public Task(Object data){
		this.data = data;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getName());
		System.out.println("starting:"+data);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data = null;
	}
	public Object getdata(){
		return this.data;
	}
	
}