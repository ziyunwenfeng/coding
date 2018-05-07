package nu;

import java.util.Scanner;

public class By {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int count = 0;
		for(int i=1;i<5;i++){
			for(int j=1;i<=4;j++){
				
					if(i!=j){
						count++;
						System.out.println(i*10+j);
					}
				}
			
		}
		System.out.println(count);
	}

}
