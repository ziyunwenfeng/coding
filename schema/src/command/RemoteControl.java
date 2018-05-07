package command;

public class RemoteControl {
	Command[] onCommands ;
	Command[] offCommands;
	Command undoCommand;
	public RemoteControl(){
		onCommands = new Command[7];
	 	offCommands = new Command[7];
	 	
		Command noCommand = null ;
		for(int i=0;i<7;i++){
			onCommands[i] = noCommand;
			offCommands[i] = noCommand;
		}
	}

	public void setCommand(int pos,Command on,Command off){
		onCommands[pos] = on;
		offCommands[pos] = off;
	}

	public void openOne(int pos){
		onCommands[pos].execute();
		undoCommand = onCommands[pos];
	}
	
	public void offOne(int pos){
		offCommands[pos].execute();
		undoCommand = offCommands[pos];
	}
	
	public void undo(){
		undoCommand.undo();;
	}
	
}
