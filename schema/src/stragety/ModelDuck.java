package stragety;

public class ModelDuck extends Duck {

	public ModelDuck(){
		quack = new QuackMouth();
		fly = new FlySwings();
	}
	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("i am a Modelduck");
	}

}
