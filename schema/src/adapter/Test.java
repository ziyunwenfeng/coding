package adapter;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Duck yellowDuck = new YellowDuck();
		Turkey turkey = new WildTurkey();
		Duck tuekeyadaptor = new TuekeyAdaptor(turkey);
		yellowDuck.quack();
		yellowDuck.fly();
		turkey.gobble();
		turkey.fly();
		tuekeyadaptor.quack();
		tuekeyadaptor.fly();
	}

}
