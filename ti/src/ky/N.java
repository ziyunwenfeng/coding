package ky;

public class N {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {1,2,3,4};
		System.out.println(search(a,0,3,0));
	}
	public static int search(int[] a,int l,int r,int x){
		int p = 0;
		if(l<=r){
			int mid = (l+r)/2;
			if(a[mid] == x){
				p =  mid;
			}else if(a[mid]>x){
				return search(a,l,mid-1,x);
			}else if(a[mid]<x){
				return search(a,mid+1,r,x);
			}
		}else{
			p =  -1;
		}
		return p;
	}
}
