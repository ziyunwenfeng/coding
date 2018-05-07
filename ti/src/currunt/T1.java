package currunt;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
class Mytest implements Runnable{
	private int num;
	public Mytest(int num){
		this.num = num;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("runing: "+num);
		try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("end: "+num);
	}
	
}
public class T1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadPoolExecutor executor = new ThreadPoolExecutor(5,10,200,TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<Runnable>(5));
		for(int i=0;i<15;i++){
			Mytest test = new Mytest(i);
			executor.execute(test);
			System.out.println("pool nums: "+executor.getPoolSize()+
					"waiting num:"+executor.getQueue().size()+
					"completed"+executor.getCompletedTaskCount());
			
		}
		executor.shutdown();
	}

}
