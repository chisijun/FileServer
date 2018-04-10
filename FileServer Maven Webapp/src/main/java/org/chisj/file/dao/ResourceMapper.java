package org.chisj.file.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.chisj.file.pojo.Resource;

public interface ResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

	/**
	 * 查询全部的图片
	 */
	List<Map<String, Object>> showAll(Resource resource);

	/**
	 * 查询图片
	 */
	List<Resource> show();
}