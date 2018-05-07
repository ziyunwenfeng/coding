package decorate;

public class HouseBlend extends Beverage {
	public HouseBlend(){
		description = "HouseBlend";
	}
	@Override
	public double cost() {
		// TODO Auto-generated method stub
		double cost=3.99;
		switch (size){
		case 1: cost = 3.99;break;
		case 2: cost = 4.99;break;
		case 3: cost = 5.99;break;
		}
		return cost;
	}


}
