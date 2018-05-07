package thread;

public class MyTask implements Runnable {
	private int taskNum;
	public MyTask(int taskNum){
		this.taskNum = taskNum;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("running  : "+taskNum);
		try {
			Thread.currentThread().sleep(4000);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("end  : "+taskNum);
	}

}
