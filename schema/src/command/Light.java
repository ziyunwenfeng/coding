package command;

public class Light {
	String lighting;
	public Light(){}
	public Light(String lighting){
		this.lighting = lighting;
	}
	public String getLighting() {
		return lighting;
	}
	public void setLighting(String lighting) {
		this.lighting = lighting;
	}
	public void lightOn(){
		System.out.println("lighting on");
	}
	public void lightOff(){
		System.out.println("lighting off");
	}
}
