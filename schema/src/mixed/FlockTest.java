package mixed;

public class FlockTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractDuckFactory duckfactory =new CounterDuck();
		Quackable blueduck = duckfactory.createBlueDuck();
		Quackable redduck = duckfactory.createRedDuck();
		
		Flock flock = new Flock();
		flock.addquack(redduck);
		flock.addquack(blueduck);
		flock.quack();
		
		Quackable redduck2 = duckfactory.createRedDuck();
		Quackable redduck3 = duckfactory.createRedDuck();
		Quackable redduck4 = duckfactory.createRedDuck();
		Quackable redduck5 = duckfactory.createRedDuck();
		Flock redduckflock = new Flock();
		redduckflock.addquack(redduck2);
		redduckflock.addquack(redduck4);
		redduckflock.addquack(redduck5);
		redduckflock.addquack(redduck3);
		
		flock.addquack(redduckflock);
		flock.quack();
		
	}

}
