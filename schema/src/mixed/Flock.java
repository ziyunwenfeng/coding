package mixed;

import java.util.ArrayList;
import java.util.Iterator;


public class Flock implements Quackable {
	
	ArrayList<Quackable> list = new ArrayList<Quackable>();
	public Flock(){}

	public void addquack(Quackable quack){
		list.add(quack);
	}
	@Override
	public void quack() {
		// TODO Auto-generated method stub
		Iterator<Quackable> it = list.iterator();
		while(it.hasNext()){
			Quackable quack = it.next();
			quack.quack();
		}
	}

	@Override
	public void notifyall() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void register(Observer obj) {
		// TODO Auto-generated method stub
		Iterator<Quackable> it = list.iterator();
		while(it.hasNext()){
			Quackable quack = it.next();
			quack.register(obj);;
		}
	}

}
