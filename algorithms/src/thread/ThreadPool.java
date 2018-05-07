package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
	public static void main(String[] args){
//		ExecutorService pool = Executors.newFixedThreadPool(5);
//		Thread1 thread11 = new Thread1();
//		Thread1 thread12 = new Thread1();
//		Thread1 thread13 = new Thread1();
//		Thread1 thread14 = new Thread1();
//		Thread1 thread15 = new Thread1();
//		Thread1 thread16 = new Thread1();
//		pool.execute(thread11);
//		pool.execute(thread12);
//		pool.execute(thread13);
//		pool.execute(thread14);
//		pool.execute(thread15);
//		pool.execute(thread16);
//		pool.shutdown();
		
//		ExecutorService pool = Executors.newCachedThreadPool();
		ExecutorService pool = Executors.newFixedThreadPool(4);
		for(int i=0;i<10;i++){
			final int count = i;
			pool.submit(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println("xiancheng: "+Thread.currentThread().getName()+"  fuzele "+count+"  cirenwu");
				}
				
			});
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
