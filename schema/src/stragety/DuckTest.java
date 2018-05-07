package stragety;

public class DuckTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Duck duck = new Mduck();
		duck.fly();
		duck.quack();
	//	duck.display();
		
		ModelDuck model = new ModelDuck();
		model.display();
		model.fly();
		model.setFly(new FlyRockets());
		model.fly();
	}

}
