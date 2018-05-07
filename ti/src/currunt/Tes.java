package currunt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Tes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ExecutorService es = Executors.newFixedThreadPool(3);
		ExecutorService es = Executors.newCachedThreadPool();
		for(int i=0;i<10;i++){
			int index = i;
			es.execute(new Runnable(){
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
					try {
						Thread.sleep(1000*index);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(index);
				}
			});
		}
	}

}
