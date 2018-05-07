package mixed;

public class Simulate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractDuckFactory duckfactory =new CounterDuck();
		Quackable blueduck = duckfactory.createBlueDuck();
		Quackable redduck = duckfactory.createRedDuck();
		
		blueduck.quack();
		redduck.quack();
		
		System.out.println(QuackCounter.getNum());
	}

}
