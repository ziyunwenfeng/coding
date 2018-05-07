package mixed;

public class DuckFactory extends AbstractDuckFactory {

	@Override
	public Quackable createBlueDuck() {
		// TODO Auto-generated method stub
		return new BuleDuck();
	}

	@Override
	public Quackable createRedDuck() {
		// TODO Auto-generated method stub
		return new RedDuck();
	}

}
