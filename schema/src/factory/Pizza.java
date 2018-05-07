package factory;

public class Pizza {
	String des;
	public Pizza(){
		
	}
	public Pizza(String type){
		this.des = type;
	}
	
	public String getPizza(){
		return des;
	}
}
