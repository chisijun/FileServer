package org.chisj.file.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.chisj.file.pojo.Gallery;
import org.chisj.file.pojo.Resource;

public interface GalleryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Gallery record);

    int insertSelective(Gallery record);

    Gallery selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Gallery record);

    int updateByPrimaryKey(Gallery record);

	List<Map<String, Object>> show(Integer id);

	List<Map<String, Object>> showAll(@Param(value="param") Gallery record);

	int deleteResource(Integer id);
}