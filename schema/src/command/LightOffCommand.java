package command;

public class LightOffCommand implements Command {
	Light light;
	public LightOffCommand(){}
	public LightOffCommand(Light light){
		this.light = light;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		light.lightOff();
	}
	@Override
	public void undo() {
		// TODO Auto-generated method stub
		light.lightOn();
	}

}
