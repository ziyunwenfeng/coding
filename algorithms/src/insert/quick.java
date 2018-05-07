package insert;

public class quick {
	public static void quicksort(int[] a){
		sort(a,0,a.length-1);
	}
	public static void sort(int[] a,int l,int r){
		if(l<r){
			int q = partation(a,l,r);
			sort(a,l,q-1);
			sort(a,q+1,r);
		}
		
	}
	public static int partation(int[] a,int l,int r){	
		int i=l-1;
		int x= a[r];
		for(int k=l;k<r;k++){
			if(a[k]<=x){
				i++;
				int tmp = a[k];
				a[k] = a[i];
				a[i] = tmp;
			}
		}
		int tmp = a[i+1];
		a[i+1] = a[r];
		a[r] = tmp;
		return i+1;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {37,81,93,6,5,7,2};
		quicksort(a);
		
		for(int i:a){
			System.out.println(i);
		}
	}

}
