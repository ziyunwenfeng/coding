package simplefactory;

public class OrderPizza {
	Pizza pizza;
	PizzaFactory factory;
	public OrderPizza(PizzaFactory factory){
		this.factory = factory;
	}
	public Pizza order(String type){
		pizza = factory.createPizza(type);
		return pizza;
	}
	
	
}
