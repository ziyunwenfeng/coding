package mixed;


public class QuackCounter implements Quackable {
	Quackable duck ;
	static int num;
	
	public QuackCounter(){}
	public QuackCounter(Quackable duck ){
		this.duck = duck;
	}
	@Override
	public void quack() {
		// TODO Auto-generated method stub
		duck.quack();
		num++;
	}
	public static int getNum(){
		return num;
	}
	
	@Override
	public void notifyall() {
		// TODO Auto-generated method stub
		duck.notifyall();
	}
	@Override
	public void register(Observer obj) {
		// TODO Auto-generated method stub
		duck.register(obj);
	}
	


}
