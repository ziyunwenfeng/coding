package thread;

public class Bank {
	private int totalNum = 0;
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public void saveMoney(int num){
		synchronized(this){
			totalNum+=num;
			System.out.println("save");
		}
	}
	public void getMoney(int num){
		Bank bank = new Bank();
		synchronized(this){
			if(num>bank.getTotalNum()){
				System.out.println("not enough");
			}
			totalNum-=num;
			System.out.println("get");
		}
	}
	public static void main(String[] args){
		Thread zhang = new Thread(new SaveMoney());
		Thread tang = new Thread(new SaveMoney());
		Thread zhou = new Thread(new SaveMoney());
		
		Thread li = new Thread(new GetMoney());
		Thread guo = new Thread(new GetMoney());
		
		zhang.start();
		tang.start();
		zhou.start();
		li.start();
		guo.start();
		
	}
}
