package mixed;

public class T {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Quackable blueduck = new BuleDuck();
		Quackable redduck = new RedDuck();
		Goose goose = new Goose();
		QuackCounter counter = new QuackCounter(blueduck);
		QuackCounter counter2 = new QuackCounter(redduck);
		blueduck.quack();
		redduck.quack();
		goose.quack();
		counter.quack();
		counter2.quack();
		
		System.out.println("times:"+counter.getNum());
	}

}
