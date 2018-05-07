package thread;

public class GetMoney implements Runnable {
	Bank bank = new Bank();
	@Override
	public void run() {
		// TODO Auto-generated method stub
		bank.getMoney(100);
	}

}
