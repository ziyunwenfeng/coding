package simplefactory;

public class SimpleFactory {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PizzaFactory fac = new PizzaFactory();
		Pizza pizza = new OrderPizza(fac).order("pizza");
		System.out.println(pizza.getPizza());
	}

}
