package command;

public class RemoteControlTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Light light = new Light();
		Command oncommand = new LightOnCommand(light);
		Command offcommand = new LightOffCommand(light);
		RemoteControl control = new RemoteControl();
		control.setCommand(0, oncommand, offcommand);
		control.openOne(0);
		control.offOne(0);
		control.undo();
	}

}
