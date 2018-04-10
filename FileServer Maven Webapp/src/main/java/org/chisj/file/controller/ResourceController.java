/**  
 * @Title: ResourceController.java
 * @Package org.chisj.file.controller
 * @Description: TODO
 * @author chisj chisj@foxmail.com
 * @date 2018年4月9日
 */
package org.chisj.file.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.chisj.file.common.web.JsonResult;
import org.chisj.file.entity.SourceType;
import org.chisj.file.pojo.Resource;
import org.chisj.file.service.ResourceService;
import org.chisj.file.utils.FileUtils;
import org.chisj.file.utils.MimeTypeUtil;
import org.chisj.file.utils.UtilDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * ClassName: ResourceController 
 * @Description: 资源管理控制器
 * @author chisj chisj@foxmail.com
 * @date 2018年4月10日
 */
@RestController
@RequestMapping("/Resource")
public class ResourceController {

	@Autowired
	private ResourceService resourceService;
	
	/**
	 * 资源上传
	 */
	@RequestMapping(value="/upload",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	public void upload(Integer galleryId, HttpServletResponse response, HttpServletRequest request) throws Throwable {
		System.out.println("galleryId=" + galleryId);
		String desc = request.getParameter("desc");
		
		List<Resource> resources = new ArrayList<Resource>();
		
		/* 将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）*/
		CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
				request.getSession().getServletContext());
		
		/* 检查form中是否有enctype="multipart/form-data" */
		if(multipartResolver.isMultipart(request)) {
			/* 将request变成多部分request */
			MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
			
			/* 获取multiRequest 中所有的文件名*/
			Iterator iter=multiRequest.getFileNames();
			String timedate  = UtilDate.getOrderNum();	// 时间戳,避免文件覆盖
			String realPath = request.getSession().getServletContext().getRealPath("/");
			while(iter.hasNext()) {
				
				//一次遍历所有文件
				MultipartFile file = multiRequest.getFile(iter.next().toString());
                if(file != null) {
                	
                	/* 文件属性获取 */
					SourceType sourceType = MimeTypeUtil.getSourceType(file.getContentType());
					
					/* MultipartFile 转 File */
					String savePath = realPath + sourceType.getSourceTypePath(); // 存储文件的目录
					// 文件名及后缀
					String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
					FileUtils.mkdirs(savePath);
					File f = new File(savePath, timedate + ext);	
				    file.transferTo(f);
                	/* 保存上传的文件属性 */
					Resource r = new Resource();
					r.setDesc(desc);
					r.setName(timedate + ext);
					r.setPath(sourceType.getSourceTypePath() + "/" + timedate + ext); // 本地存储相对路径
					r.setTypeId(Integer.parseInt(sourceType.getSourceTypeId()));
					r.setGalleryId(galleryId);
					// 添加资源记录
					resourceService.add(r);
					resources.add(r);
                }
            }
        }
        
        /* 返回上传结果 */
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/x-json;charset=UTF-8");
        /* 解决文件上传跨域问题 */
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        // 默认只上传了一个文件
        JSONObject jsObject =JSONObject.fromObject(new JsonResult(true, "文件上传成功", resources));
        
        response.getWriter().write(jsObject.toString());
		
	}
	
	/**
	 * 资源删除
	 */
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public JsonResult delete(Integer id, HttpServletRequest request) {
		
		Resource r = resourceService.resourceShowById(id);
		
		/* 删除资源 */
		//OSSManageUtil.deleteFile(OSSConfigure.getOssConfig(), r.getResourcepath());
		String realPath = request.getSession().getServletContext().getRealPath("/");
		File f = new File(realPath + r.getPath());
		if (f.exists() && f.isFile()) {
			f.delete();
		}
		
		int result = resourceService.del(id);
				
		return new JsonResult(true,"删除资源信息成功",null);
		
	}
	
	/**
	 * 资源查看
	 */
	@RequestMapping(value="/showAll",method=RequestMethod.POST)
	public JsonResult showAll(Resource resource, HttpServletRequest request) {
		
		List<Map<String, Object>> resources = resourceService.showAll(resource);
		
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		for (Map<String, Object> map : resources) {
			Object obj = map.get("path");
			map.put("path", basePath + obj);
		}
		
		return new JsonResult(true,"查询资源信息成功", resources);
		
	}
	
	/**
	 * 资源查看
	 */
//	@RequestMapping(value="/showAll",method=RequestMethod.POST)
//	public JsonResult showAll(Resource resource, HttpServletRequest request) {
//		
		//List<Map<String, Object>> resources = resourceService.showAll(resource);
		
		//分页处理，显示第一页的10条数据
//        PageHelper.startPage(1, 1000);
		
//		List<Resource> resources = resourceService.show();
		
//		String path = request.getContextPath();
//		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//		
//		for (Map<String, Object> map : resources) {
//			Object obj = map.get("path");
//			map.put("path", basePath + obj);
//		}
		
		// 分页
//        PageInfo<Resource> pageInfo = new PageInfo<>(resources);
//		
//		return new JsonResult(true,"查询资源信息成功", pageInfo);
//		
//	}
}
