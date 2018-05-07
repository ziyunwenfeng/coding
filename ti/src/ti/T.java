package ti;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class T {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		int count=0;
		while(scanner.hasNext()){
			int n = scanner.nextInt();
			Set<Integer> set = new TreeSet<Integer>();
			for(int i=0;i<n;i++){
				set.add(scanner.nextInt());
			}
			if(set.size()<3)
				System.out.println(-1);
			else{
				Iterator<Integer> it = set.iterator();
				while(it.hasNext()){
					count++;
					it.next();
					if(count==2){
						System.out.println(it.next());
					}
					
				}
			}
		}
		
	}
	public int[] twoSum(int[] numbers, int target) {
		int[] s = new int[2];
        for(int i=0;i<numbers.length;i++){
        	for(int j=i+1;j<numbers.length;j++){
        		if(numbers[i]+numbers[j]==target){
        			s[0] = i;
        			s[1] = j;
        		}
        }
       
    }
        return s;
	}
	
	public double findMedianSortedArrays(int A[], int B[]) {

        int m = A.length;
        int n = B.length;
        int mid = (m+n)/2;
        int k= 0;
        int i =0;
        int j = 0;
        double r = 0;
        while(i<m&&j<n){
        	if(A[i]<=B[j]){
        		i++;
        		k++;
        		if(k==mid)
            		r = A[i];
        	}
        	else{
        		j++;
        		k++;
        		if(k==mid)
            		r = B[j];
        	}
        	
        }
        return r;
	}
}
