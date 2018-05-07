package thread;

import java.util.LinkedList;
import java.util.List;

public class Storage {
	final int Max = 10; 
	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

	public int getMax() {
		return Max;
	}

	List<Object> list = new LinkedList<Object>();
	
	public synchronized void produce(int num){
		if(num+list.size()>Max){
			System.out.println("taiduole______________________");
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i=0;i<num;i++){
			list.add(i);
		}
		System.out.println("shengchanle: "+num);
		notifyAll();
	}
	
	public synchronized void consume(int num){
		if(num>list.size()){
			System.out.println("buzu>>>>>>>>>>>>>>>>>>>");
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i=0;i<num;i++){
			list.remove(i);
		}
		System.out.println("xiaofeile:::"+num);
		notifyAll();
	}
}
