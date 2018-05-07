package thread;

public class Thread2 extends Thread {
	private int num;
	private Storage storge;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Storage getStorge() {
		return storge;
	}

	public void setStorge(Storage storge) {
		this.storge = storge;
	}

	public Thread2() {

	}

	public Thread2(Storage storge) {
		this.storge = storge;
	}

	@Override
	public void run() {
		System.out.println("in thread2 consume");
		storge.consume(num);
	}

}
