package mixed;

import java.util.ArrayList;
import java.util.Iterator;


public class Observable implements QuackObservable {
	ArrayList<Observer> observers = new ArrayList<Observer>();
	QuackObservable duck ;
	public Observable(){}
	public Observable(QuackObservable duck){
		this.duck = duck;
	}

	@Override
	public void notifyall() {
		// TODO Auto-generated method stub
		Iterator<Observer> it = observers.iterator();
		while(it.hasNext()){
			it.next().update(duck);
		}
	}
	@Override
	public void register(Observer obj) {
		// TODO Auto-generated method stub
		observers.add(obj);
	}

	

	

}
