package mixed;

public class CounterDuck extends AbstractDuckFactory {

	@Override
	public Quackable createBlueDuck() {
		// TODO Auto-generated method stub
		return new QuackCounter(new BuleDuck());
	}

	@Override
	public Quackable createRedDuck() {
		// TODO Auto-generated method stub
		return new QuackCounter(new RedDuck());
	}

}
