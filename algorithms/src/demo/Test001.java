package demo;

public class Test001 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(plus("19","111999"));
	}
	public static String plus(String str1,String str2){
		String res = "";
		int i=str1.length()-1;
		int j=str2.length()-1;
		int a = 0;
		int tmp ;
		while(i>=0&&j>=0){
			if(a == 1)
				tmp = Integer.valueOf(String.valueOf(str1.charAt(i)))+Integer.valueOf(String.valueOf(str2.charAt(j)))+1;
			else
				tmp = Integer.valueOf(String.valueOf(str1.charAt(i)))+Integer.valueOf(String.valueOf(str2.charAt(j)));
			if(tmp>=10){
				tmp = tmp%10;
				a = 1;
			}			
			else 
				a = 0;
			res = String.valueOf(tmp)+res;
			i--;
			j--;
		}
		
		if(i>0){
			res = str1.substring(0, i+1)+res;
		}
		if(j>0){
			res = str2.substring(0, j+1)+res;
		}
		return res;
	}

}
