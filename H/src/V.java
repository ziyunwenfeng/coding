import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

class Stu implements Comparable<Stu>{
	private int id;
	private String name;
	public Stu(int id,String name){
		this.id = id;
		this.name = name;
	}
	@Override
	public int compareTo(Stu o) {
		// TODO Auto-generated method stub
		return this.id-o.id;
	}
	@Override
	public String toString(){
		return id+" "+name;
	}

}
public class V {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stu s1 = new Stu(3,"a");
		Stu s2 = new Stu(1,"b");
		Stu s3 = new Stu(2,"v");
		Set<Stu> set = new TreeSet<Stu>();
		set.add(s1);
		set.add(s2);
		set.add(s3);
		for(Stu s:set)
			System.out.println(s);
		List l = new LinkedList();
		
	}
 
}
