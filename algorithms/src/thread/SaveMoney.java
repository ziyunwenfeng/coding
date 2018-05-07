package thread;

public class SaveMoney implements Runnable{
	Bank bank = new Bank();
	@Override
	public void run() {
		// TODO Auto-generated method stub
		bank.saveMoney(100);
	}

}
