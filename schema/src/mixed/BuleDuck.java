package mixed;


public class BuleDuck implements Quackable {
	Observable observable;
	
	public BuleDuck(){
		observable = new Observable(this);
	}
	@Override
	public void quack() {
		// TODO Auto-generated method stub
		System.out.println("blueduck quack");
		notifyall();
	}

	@Override
	public void notifyall() {
		// TODO Auto-generated method stub
		observable.notifyall();
	}
	@Override
	public void register(mixed.Observer obj) {
		// TODO Auto-generated method stub
		observable.register(obj);
	}

	

}
