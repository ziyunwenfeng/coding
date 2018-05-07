package thread;

public class Cousumer implements Runnable {
	Source source;
	public Cousumer(Source source){
		this.source = source;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			source.destory();
		}
	}

}
