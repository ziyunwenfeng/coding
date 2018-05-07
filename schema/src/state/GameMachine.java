package state;

public class GameMachine {
	Stat Nocoin ;
	Stat Hascoin;
	Stat state = Nocoin;
	int count = 0;
	public GameMachine(){}
	public GameMachine(int num){
		this.count = num;
		Nocoin = new NoCoinStat(this);
		Hascoin = new HasCoinStat(this);
	}
	public Stat getState() {
		return state;
	}
	public void setState(Stat state) {
		this.state = state;
	}
	public void insertCoin(){
		state.insert();
	}
	public void deleteCoin(){
		state.delete();
	}
	public Stat getNocoin() {
		return Nocoin;
	}
	public Stat getHascoin() {
		return Hascoin;
	}
	public void setHascoin(Stat hascoin) {
		Hascoin = hascoin;
	}
	public void setNocoin(Stat nocoin) {
		Nocoin = nocoin;
	}
	
	
	
	
}
