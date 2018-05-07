package insert;

public class merge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {3,1,3,6,5,7,2};
		mergesort(a);
		for(int i:a){
			System.out.println(i);
		}
	}
	public static void mergesort(int[] a){
		sort(a,0,a.length-1);
	}
	public static void sort(int[] a,int l,int r){
		if(l>=r)
			return;
		int mid = (l+r)/2;
		sort(a,l,mid);
		sort(a,mid+1,r);
		mergetwo(a,l,mid,r);
	}
	public static void mergetwo(int[] a,int l,int mid,int r){
		int[] tmp = new int[a.length];
		int i= l;
		int k = mid+1;
		int p = l;
		while(i<=mid&&k<=r){
			if(a[i]<=a[k]){
				tmp[p]=a[i];
				p++;
				i++;
			}else{
				tmp[p]=a[k];
				p++;
				k++;
			}
		}
		while(i<=mid){
			tmp[p]=a[i];
			p++;
			i++;
		}
		while(k<=r){
			tmp[p]=a[k];
			p++;
			k++;
		}
		for(int j=l;j<=r;j++){
			a[j] = tmp[j];
		}
	}
} 
