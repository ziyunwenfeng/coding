package decorate;

public abstract class Beverage {
	String description = "unknown";
	int size = 0;
	public String getDescription(){
		return description;
	}
	
	public abstract double cost();
}
