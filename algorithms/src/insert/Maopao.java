package insert;

public class Maopao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {3,1,3,6,5,7,2};
		mao(a);
		for(int i:a){
			System.out.println(i);
		}
	}
	public static void mao(int[] a){
		for(int i=0;i<a.length-1;i++){
			for(int j=0;j<a.length-i-1;j++){
				if(a[j]>a[j+1]){
					int tmp = a[j];
					a[j] = a[j+1];
					a[j+1] = tmp;
				}
			}
		}
	}
}
