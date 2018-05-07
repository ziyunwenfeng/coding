package adapter;

public class TuekeyAdaptor implements Duck {
	Turkey turkey ;
	public TuekeyAdaptor(Turkey turkey){
		this.turkey = turkey;
	}
	@Override
	public void quack() {
		// TODO Auto-generated method stub
		turkey.gobble();
	}

	@Override
	public void fly() {
		// TODO Auto-generated method stub
		turkey.fly();
	}

}
