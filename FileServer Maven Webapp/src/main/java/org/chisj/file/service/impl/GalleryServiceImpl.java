/**  
 * @Title: GallertServiceImpl.java
 * @Package org.chisj.file.service.impl
 * @Description: TODO
 * @author chisj chisj@foxmail.com
 * @date 2018年4月10日
 */
package org.chisj.file.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.chisj.file.dao.GalleryMapper;
import org.chisj.file.pojo.Gallery;
import org.chisj.file.service.GalleryService;
import org.springframework.stereotype.Service;

/**
 * ClassName: GallertServiceImpl 
 * @Description: 相册管理接口实现
 * @author chisj chisj@foxmail.com
 * @date 2018年4月10日
 */
@Service("galleryService")
public class GalleryServiceImpl implements GalleryService {

	@Resource
	private GalleryMapper galleryDao;
	
	/* (non-Javadoc)
	 * @see org.chisj.file.service.GalleryService#add(org.chisj.file.pojo.Gallery)
	 */
	@Override
	public int add(Gallery gallery) {
		// TODO Auto-generated method stub
		return galleryDao.insertSelective(gallery);
		//return 0;
	}

	/* (non-Javadoc)
	 * @see org.chisj.file.service.GalleryService#delete(java.lang.Integer)
	 */
	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		
		return galleryDao.deleteByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see org.chisj.file.service.GalleryService#show()
	 */
	@Override
	public List<Map<String, Object>> show(Integer id) {
		// TODO Auto-generated method stub
		return galleryDao.show(id);
	}

	/* (non-Javadoc)
	 * @see org.chisj.file.service.GalleryService#showAll()
	 */
	@Override
	public List<Map<String, Object>> showAll(Gallery gallery) {
		// TODO Auto-generated method stub
		return galleryDao.showAll(gallery);
	}

	/* (non-Javadoc)
	 * @see org.chisj.file.service.GalleryService#deleteResource(java.lang.Integer)
	 */
	@Override
	public int deleteResource(Integer id) {
		// TODO Auto-generated method stub
		return galleryDao.deleteResource(id);
	}

	
	
}
