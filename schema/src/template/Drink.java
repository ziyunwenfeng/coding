package template;

public abstract class Drink {
	public void prepare(){
		boil();
		make();
	}
	public void boil(){
		System.out.println("boil water");
	};
	public abstract void make(); 
}
