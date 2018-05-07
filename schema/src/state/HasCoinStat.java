package state;

public class HasCoinStat implements Stat {
	GameMachine machine;
	public HasCoinStat(){}
	public HasCoinStat(GameMachine machine){
		this.machine = machine;
	}
	@Override
	public void insert() {
		// TODO Auto-generated method stub
		System.out.println("you cannnot insert coin");
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		System.out.println("get coin");
		machine.setState(machine.getNocoin());
	}

}
