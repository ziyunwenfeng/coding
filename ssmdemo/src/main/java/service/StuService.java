package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import dao.StuMapper;
import entity.Stu;

@Service
public class StuService implements IStuService{
	@Autowired
	StuMapper mapper;
	public void insertStu(Stu stu){
		mapper.insert(stu);
	}
	public Stu getByName(String name) {
		// TODO Auto-generated method stub
		return mapper.findById(name);
	}
	public List<Stu> getAll() {
		// TODO Auto-generated method stub
		return mapper.getAll();
	}
	
}
