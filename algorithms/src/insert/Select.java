package insert;

public class Select {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {3,1,3,6,5,7,2};
		selectsort(a);
		
		for(int i:a){
			System.out.println(i);
		}
	}
	public static void selectsort(int[] a){
		for(int i=0;i<a.length;i++){
			int min=i;
			for(int j=i+1;j<a.length;j++){
				if(a[j]<a[min]){
					min = j;
				}
			}
			if(min!=i){
				int tmp = a[min];
				a[min] = a[i];
				a[i] = tmp;
			}
		}
	}
}
