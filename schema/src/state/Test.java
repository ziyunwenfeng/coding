package state;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameMachine machine = new GameMachine(5);
		machine.getState();
	//	machine.insertCoin();
		machine.deleteCoin();
		System.out.println(machine);
	}

}
