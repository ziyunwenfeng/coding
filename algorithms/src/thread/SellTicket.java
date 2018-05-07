package thread;

public class SellTicket implements Runnable{
	static int num = 10;
	public synchronized void sell(){
		while(num>0){
			num--;
			System.out.print(num+"  ");
		}
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		sell();
		
	}
	
}
