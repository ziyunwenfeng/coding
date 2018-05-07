package simplefactory;

public class PizzaFactory {
	Pizza pizza;
	public Pizza createPizza(String type){
		
		if(type.equals("chesepizza")){
			pizza = new ChesePizza();
		}
		if(type.equals("pizza")){
			pizza = new Pizza();
		}
		return pizza;
	}
}
