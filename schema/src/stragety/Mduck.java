package stragety;

public class Mduck extends Duck {
	public Mduck(){
		quack = new QuackMouth();
		fly = new FlySwings();
	}
	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("i am a Mduck");
	}

}
