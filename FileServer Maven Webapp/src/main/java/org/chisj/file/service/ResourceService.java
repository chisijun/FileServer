/**  
 * @Title: ResourceService.java
 * @Package org.chisj.file.service
 * @Description: TODO
 * @author chisj chisj@foxmail.com
 * @date 2018年4月9日
 */
package org.chisj.file.service;

import java.util.List;
import java.util.Map;

import org.chisj.file.pojo.Resource;

/**
 * ClassName: ResourceService 
 * @Description: 资源服务接口
 * @author chisj chisj@foxmail.com
 * @date 2018年4月9日
 */
public interface ResourceService {

	/**
	 * 添加
	 */
	public int add(Resource resource);
	
	/**
	 * 删除
	 */
	public int del(Integer id);
	
	/**
	 * 更新
	 */
	public int update(Resource resource);
	
	/**
	 * 查询全部
	 */
	public List<Map<String, Object>> showAll(Resource resource);

	/**
	 * 根据Id查询资源信息
	 */
	public Resource resourceShowById(Integer id);

	/**
	 * 查询
	 */
	public List<Resource> show();
}
