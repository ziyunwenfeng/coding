package mixed;


public class GooseAdaptor implements Quackable {
	Goose goose;
	Observable observable;
	public GooseAdaptor(){}
	public GooseAdaptor(Goose goose){
		this.goose = goose;
	}
	@Override
	public void quack() {
		// TODO Auto-generated method stub
		goose.quack();
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
