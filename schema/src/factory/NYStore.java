package factory;

public class NYStore extends PizzaStore {
	
	@Override
	Pizza createPizza(String type) {
		// TODO Auto-generated method 
		Pizza pizza = null;
		if(type.equals("NY"))
			pizza = new NYPizza(type);
		if(type.equals("Pizza"))
			pizza = new Pizza(type);
		return pizza;
	}

}
