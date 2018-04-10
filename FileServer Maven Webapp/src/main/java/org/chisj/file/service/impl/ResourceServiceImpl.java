/**  
 * @Title: ResourceServiceImpl.java
 * @Package org.chisj.file.service.impl
 * @Description: TODO
 * @author chisj chisj@foxmail.com
 * @date 2018年4月9日
 */
package org.chisj.file.service.impl;

import java.util.List;
import java.util.Map;

import org.chisj.file.dao.ResourceMapper;
import org.chisj.file.pojo.Resource;
import org.chisj.file.service.ResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * ClassName: ResourceServiceImpl 
 * @Description: 资源服务接口实现类
 * @author chisj chisj@foxmail.com
 * @date 2018年4月9日
 */
@Transactional
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {

	@javax.annotation.Resource
	private ResourceMapper resourceDao;
	
	/* (non-Javadoc)
	 * @see org.chisj.file.service.ResourceService#add(org.chisj.file.pojo.Resource)
	 */
	@Override
	public int add(Resource resource) {
		// TODO Auto-generated method stub
		return resourceDao.insertSelective(resource);
	}

	/* (non-Javadoc)
	 * @see org.chisj.file.service.ResourceService#del(java.lang.Integer)
	 */
	@Override
	public int del(Integer id) {
		// TODO Auto-generated method stub
		return resourceDao.deleteByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see org.chisj.file.service.ResourceService#update(org.chisj.file.pojo.Resource)
	 */
	@Override
	public int update(Resource resource) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.chisj.file.service.ResourceService#showAll(org.chisj.file.pojo.Resource)
	 */
	@Override
	public List<Map<String, Object>> showAll(Resource resource) {
		// TODO Auto-generated method stub
		return resourceDao.showAll(resource);
	}

	/* (non-Javadoc)
	 * @see org.chisj.file.service.ResourceService#resourceShowById(java.lang.Integer)
	 */
	@Override
	public Resource resourceShowById(Integer id) {
		// TODO Auto-generated method stub
		return resourceDao.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see org.chisj.file.service.ResourceService#show()
	 */
	@Override
	public List<Resource> show() {
		// TODO Auto-generated method stub
		return resourceDao.show();
	}

}
