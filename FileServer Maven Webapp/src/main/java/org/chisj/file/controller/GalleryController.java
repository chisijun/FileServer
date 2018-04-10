/**  
 * @Title: GalleryController.java
 * @Package org.chisj.file.controller
 * @Description: TODO
 * @author chisj chisj@foxmail.com
 * @date 2018年4月10日
 */
package org.chisj.file.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.chisj.file.common.web.JsonResult;
import org.chisj.file.pojo.Gallery;
import org.chisj.file.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: GalleryController 
 * @Description: 相册管理控制器
 * @author chisj chisj@foxmail.com
 * @date 2018年4月10日
 */
@RestController
@RequestMapping("/Gallery")
public class GalleryController {

	@Autowired
	private GalleryService galleryService;
	
	/**
	 * 相册添加
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public JsonResult add(Gallery gallery) {
		
		galleryService.add(gallery);
		
		return new JsonResult(true,"添加相册信息成功",gallery);
	}
	
	/**
	 * 相册删除
	 */
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public JsonResult delete(Integer id, HttpServletRequest request) {
		
		// 查询全部的资源并删除
		List<Map<String, Object>> gallerys = galleryService.show(id);
		String realPath = request.getSession().getServletContext().getRealPath("/");
		
		for (Map<String, Object> map : gallerys) {
			File f = new File(realPath + map.get("path"));
			if (f.exists() && f.isFile()) {
				f.delete();
			}
		}
		// 删除资源记录
		galleryService.deleteResource(id);
		galleryService.delete(id);
		
		return new JsonResult(true,"删除相册信息成功",null);
	}
	
	/**
	 * 相册查看
	 */
	@RequestMapping(value="/show",method=RequestMethod.POST)
	public JsonResult show(Integer id) {
		
		List<Map<String, Object>> gallerys = galleryService.show(id);
		
		return new JsonResult(true,"查看相册信息成功",gallerys);
	}
	
	/**
	 * 相册查看全部
	 */
	@RequestMapping(value="/showAll",method=RequestMethod.POST)
	public JsonResult showAll(Gallery gallery) {
		
		List<Map<String, Object>> gallerys = galleryService.showAll(gallery);
		
		return new JsonResult(true,"查看全部相册信息成功",gallerys);
	}
	
	
}
