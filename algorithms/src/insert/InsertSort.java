package insert;

public class InsertSort {
	public static void insert(int[] a){
		for(int i=1;i<=a.length-1;i++){
			int tmp = a[i];
			int j=i-1;
			while(j>=0&&a[j]<tmp){
				a[j+1]=a[j];
				j--;
			}
			a[j+1]=tmp;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {3,1,3,6,5,7,2};
		insert(a);
		for(int i:a){
			System.out.println(i);
		}
	}
	

}
