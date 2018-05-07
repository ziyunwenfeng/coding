package serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.IDictDao;
import model.Dict;
import service.IDictService;
@Service
public class DictServiceImpl implements IDictService{
	 /**
     * 自动装配
     */
    @Autowired
    private IDictDao dictDao;
    /**
     * 根据字段获取字典
     * @param field
     * @return
     */
    public List<Dict> getDictByField(String field){
        return dictDao.getDictByField(field);
    }
}
