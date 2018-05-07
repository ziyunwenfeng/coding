package currunt;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class T3 {
	public void createPool(){
		// TODO Auto-generated method stub
		ThreadPoolExecutor pool = new ThreadPoolExecutor(2,4,3,
				TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(3),
				new ThreadPoolExecutor.DiscardOldestPolicy());
		for(int i=0;i<10;i++){
			try {
				Thread.sleep(1);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			while(getQueueSize(pool.getQueue())>=3){
				System.out.println("queue full,wait for three seconds");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			pool.execute(new MyTask(i));
			
			
		}
		pool.shutdown();
	}
	
	public synchronized int getQueueSize(Queue q){
		return q.size();
	}
	public static void main(String[] args) {
		T3 test = new T3();
		test.createPool();
	}
}



class MyTask implements Runnable{
	private Object data;
	public MyTask(Object data){
		this.data = data;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getName()+":"+data);
///		System.out.println("starting:"+data);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	data = null;
	}
	public Object getdata(){
		return this.data;
	}
	
}