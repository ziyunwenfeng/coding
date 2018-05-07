package observer;

import java.util.ArrayList;

public class WhetherData implements Subject {
	ArrayList<Observer> observers; 
	private float tem;
	private float hum;
	private float pre;
	public WhetherData(){
		observers  = new ArrayList<Observer>();
	}
	
	@Override
	public void registerObserver(Observer obj) {
		// TODO Auto-generated method stub
		observers.add(obj);
	}

	@Override
	public void removeObserver(Observer obj) {
		// TODO Auto-generated method stub
		int i = observers.indexOf(obj);
		observers.remove(i);
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		for(Observer o: observers){
			o.update(tem, hum, pre);
		}
	}
	public void dataChanged(){
		notifyObservers();
	}
	public void dataSet(float tem,float hum,float pre){
		this.hum = hum;
		this.tem = tem;
		this.pre = pre;
		dataChanged();
	}

}
