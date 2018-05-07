package thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadPoolExecutor executor = 
				new ThreadPoolExecutor(5,10,200,TimeUnit.MICROSECONDS,new ArrayBlockingQueue<Runnable>(5));
		for(int i=0;i<20;i++){
			MyTask myTask = new MyTask(i);
			executor.execute(myTask);
			System.out.println("线程池数目  : "+executor.getPoolSize());
			System.out.println("等待执行数目  : "+executor.getQueue().size());
			System.out.println("执行完数目  : "+executor.getCompletedTaskCount());
		}
		executor.shutdown();
	}

}
