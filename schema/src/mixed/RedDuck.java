package mixed;


public class RedDuck implements Quackable {
	Observable observable;
	public RedDuck(){
		observable = new Observable(this);
	}
	
	@Override
	public void quack() {
		// TODO Auto-generated method stub
		System.out.println("RedDuck quack");
		notifyall();
	}

	@Override
	public void register(Observer obj) {
		// TODO Auto-generated method stub
		observable.register(obj);
	}

	@Override
	public void notifyall() {
		// TODO Auto-generated method stub
		observable.notifyall();
	}

}
