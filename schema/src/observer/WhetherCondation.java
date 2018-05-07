package observer;

public class WhetherCondation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WhetherData whetherdata = new WhetherData();
		DataDisplay dis = new DataDisplay(whetherdata);
		whetherdata.dataSet(98, 32, 45);
		dis.update(9, 3, 5);
	}

}
