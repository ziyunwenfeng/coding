package thread;

public class TestSellTicket {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SellTicket sell = new SellTicket();
		new Thread(sell).start();
		new Thread(sell).start();
		new Thread(sell).start();
	}

}
