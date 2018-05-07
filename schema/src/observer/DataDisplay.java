package observer;

public class DataDisplay implements Observer,displayment{
	private float tem;
	private float hum;
	private float pre;
	private WhetherData whetherdata;
	public DataDisplay(WhetherData data){
		this.whetherdata  = data;
		whetherdata.registerObserver(this);
	}
	@Override
	public void update(float tem, float hum, float pre) {
		// TODO Auto-generated method stub
		this.tem = tem;
		this.hum = hum;
		this.pre = pre;
		display();
	}
	@Override
	public void display(){
		System.out.println("curtunt:tem "+tem+"hum "+hum+"pre "+pre);
	}

}
