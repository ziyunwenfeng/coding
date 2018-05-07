package decorate;

public class Mocha extends Condiment {
	Beverage beverage;
	
	public Mocha(Beverage beverage){
		this.beverage = beverage;
	}
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return beverage.getDescription()+" ,Mocha";
	}

	@Override
	public double cost() {
		// TODO Auto-generated method stub
		double cost = 0;
		switch (getSize()){
		case 1: cost = 1.99;break;
		case 2: cost = 2.99;break;
		case 3: cost = 3.99;break;
		}
		return cost+beverage.cost();	
	
	}
	@Override
	public void setSize(int s) {
		// TODO Auto-generated method stub
		this.size = s;
	}
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return this.size;
	}
	
	
	

}
