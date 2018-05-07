import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Account{
	private double balance;
	public synchronized void in(double num){
		double newbalance = balance + num; 
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		balance = newbalance;
	}
	public double get(){
		return balance;
	}
}
class Get implements Runnable{
	Account account;
	int num;
	public Get(Account account,int num){
		this.account = account;
		this.num = num;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized(account){
			account.in(num);
		}
	}
	
	public void copy(String source,String target){
		try {
			InputStream in = new FileInputStream(source);
			OutputStream out = new FileOutputStream(target);
			byte[] buffer = new byte[4096];
			int read;
			try {
				while((read=in.read(buffer))!=-1){
					out.write(buffer, 0, read);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
public class Bank {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Account account = new Account();
		ExecutorService es = Executors.newFixedThreadPool(100);
		for(int i=0;i<100;i++){
			es.execute(new Get(account,1));
		}
		es.shutdown();
		while(!es.isTerminated()){}
		System.out.println(account.get());
	}

}
