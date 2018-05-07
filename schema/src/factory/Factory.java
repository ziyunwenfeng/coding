package factory;

public class Factory {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PizzaStore store = new NYStore();
		Pizza pizza = store.orderPizza("Pizza");
		System.out.println(pizza.getPizza());
		Pizza pizza2 = store.orderPizza("NY");
		System.out.println(pizza2.getPizza());
	}

}
