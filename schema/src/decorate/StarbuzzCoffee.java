package decorate;

public class StarbuzzCoffee {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Beverage beverage = new Espresso();
//		beverage.setSize(3);
//		System.out.println(beverage.getDescription()+" "+beverage.cost());
		
		beverage = new Mocha(beverage);
		((Mocha) beverage).setSize(1);
		System.out.println(((Mocha) beverage).getSize());
		System.out.println(beverage.getDescription()+" "+beverage.cost());
		
	}

}
