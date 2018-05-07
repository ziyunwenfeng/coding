package stragety;

public abstract class Duck {
	Quackable quack;
	Fliable fly ;
	public Duck(){}
	public abstract void display();
	public void quack(){
		quack.quack();
	}
	public void fly(){
		fly.fly();
	}
	public void swim(){
		System.out.println("duck swim");
	}
	public void setQuack(Quackable quack) {
		this.quack = quack;
	}
	public void setFly(Fliable fly) {
		this.fly = fly;
	}
	
} 
