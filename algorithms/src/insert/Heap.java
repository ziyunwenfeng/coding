package insert;

public class Heap {
	static int heapsize = 7;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {3,1,3,6,5,7,2};
		Heapsort(a);
		
		for(int i:a){
			System.out.println(i);
		}
	}
	public static void Heapsort(int[] a){
		BuiltHeap(a);
		
		for(int j=a.length-1;j>=1;j--){
			int tmp = a[0];
			a[0] = a[j];
			a[j] = tmp;
			heapsize--;
			maxHeap(a,0);
		}
	}
	public static void BuiltHeap(int[] a){
		
		for(int i=a.length/2-1;i>=0;i--){
			maxHeap(a,i);
		}
	}
	public static void maxHeap(int[] a,int i){
		int l = 2*i+1;
		int r = 2*i+2;
		int large=0;
		if(l<heapsize && a[l]>a[i]){
			large=l;
		}
		else large = i;
		if(r<heapsize && a[r]>a[large]){
			large = r;
		}
		if(large!=i){
			int tmp = a[large];
			a[large] = a[i];
			a[i] = tmp;
			maxHeap(a,large);
		}
	}
}
