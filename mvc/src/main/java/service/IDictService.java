package service;

import java.util.List;

import model.Dict;

public interface IDictService {
	/**
     * 根据字段获取字典
     * @param field
     * @return
     */
    public List<Dict> getDictByField(String field);
}
