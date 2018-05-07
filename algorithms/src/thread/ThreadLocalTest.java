package thread;

import java.util.Random;

public class ThreadLocalTest {
	private static ThreadLocal threadLocals = new ThreadLocal();

	static class TestA {
		void getData(){
			System.out.println(Thread.currentThread().getName()+"get data  "+threadLocals.get());
		}
	}

	static class TestB {
		void getData(){
			System.out.println(Thread.currentThread().getName()+"get data  "+threadLocals.get());
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println("put data : " + Thread.currentThread().getName());
					int data = new Random().nextInt();
					threadLocals.set(data);
					new TestA().getData();
					new TestB().getData();
				}

			}).start();
		}

	}

}
