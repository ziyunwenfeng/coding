package mixed;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Quackable blueduck = new BuleDuck();
		Quackable redduck = new RedDuck();
		Goose goose = new Goose();
		Quackable gooseadaptor = new GooseAdaptor(goose);
		
		blueduck.quack();
		redduck.quack();
		goose.quack();
		gooseadaptor.quack();
		
	}

}
