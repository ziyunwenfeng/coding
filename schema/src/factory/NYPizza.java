package factory;

public class NYPizza extends Pizza{
	String des ;
	public NYPizza(){}
	public NYPizza(String type){
		this.des = type;
	}
	public String getPizza(){
		return des;
	}
}
