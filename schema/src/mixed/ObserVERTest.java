package mixed;

public class ObserVERTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractDuckFactory duckfactory =new CounterDuck();
		Quackable blueduck = duckfactory.createBlueDuck();
		Quackable redduck = duckfactory.createRedDuck();
		
		Flock flock = new Flock();
		flock.addquack(redduck);
		flock.addquack(blueduck);
		flock.addquack(redduck);
		flock.addquack(blueduck);
		QuockLogist logist = new QuockLogist();
		flock.register(logist);
		
		flock.quack();
		System.out.println(QuackCounter.getNum());
	}

}
