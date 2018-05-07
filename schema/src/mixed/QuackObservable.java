package mixed;


public interface QuackObservable {
	public void register(Observer obj);
	
	public void notifyall();
}
