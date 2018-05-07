package thread;

public class Source {
	private int num = 0;
	public synchronized void create(){
		if(num>10){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			num++;
			System.out.println("creater:"+Thread.currentThread().getName()+" "+num);
			
			notifyAll();
		}
		
	}
	
	public synchronized void destory(){
		if(num<=0){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			num--;
			System.out.println("destory:"+Thread.currentThread().getName()+" "+num);
			
			notifyAll();
		}
		
	}
}
