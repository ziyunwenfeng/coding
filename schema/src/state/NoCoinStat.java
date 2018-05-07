package state;

public class NoCoinStat implements Stat {
	GameMachine machine;
	public NoCoinStat(){}
	public NoCoinStat(GameMachine machine){
		this.machine = machine;
	}
	@Override
	public void insert() {
		// TODO Auto-generated method stub
		System.out.println("insert coin");
		machine.setState(machine.getHascoin());
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		System.out.println("no goin");
		
	}

}
