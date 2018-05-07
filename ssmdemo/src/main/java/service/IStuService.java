package service;




import java.util.List;

import entity.Article;
import entity.Stu;

public interface IStuService {
	
	
	public void insertStu(Stu stu);
	
	public Stu getByName(String name);
	
	public List<Stu> getAll();
	

}
