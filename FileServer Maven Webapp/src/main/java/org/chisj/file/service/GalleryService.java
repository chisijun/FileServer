/**  
 * @Title: GalleryService.java
 * @Package org.chisj.file.service
 * @Description: TODO
 * @author chisj chisj@foxmail.com
 * @date 2018年4月10日
 */
package org.chisj.file.service;

import java.util.List;
import java.util.Map;

import org.chisj.file.pojo.Gallery;

/**
 * ClassName: GalleryService 
 * @Description: 相册管理接口
 * @author chisj chisj@foxmail.com
 * @date 2018年4月10日
 */
public interface GalleryService {

	public int add(Gallery gallery);
	
	public int delete(Integer id);
	
	public List<Map<String, Object>> show(Integer id);

	public List<Map<String,Object>> showAll(Gallery gallery);

	public int deleteResource(Integer id);
}
