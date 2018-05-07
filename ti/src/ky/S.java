package ky;

public class S {
	static int count = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<10;i++){
			new Thread(new Runnable(){
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					for(int j=0;j<100;j++){
						addCount();
					}
					System.out.println(count);
				}
				
			}).start();
		}
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(count);
		
	}
	public static synchronized void addCount(){
		count++;
	}

}
