package dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import entity.Stu;
import entity.StuExample;

@Repository
public interface StuMapper {
    long countByExample(StuExample example);

    int deleteByExample(StuExample example);

    int insert(Stu record);

    int insertSelective(Stu record);

    List<Stu> selectByExample(StuExample example);

    int updateByExampleSelective(@Param("record") Stu record, @Param("example") StuExample example);

    int updateByExample(@Param("record") Stu record, @Param("example") StuExample example);
    
    Stu findById(String name);
    List<Stu> getAll();
}