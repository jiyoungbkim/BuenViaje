package edu.iot.common.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.iot.common.dao.GalleryDao;
import edu.iot.common.model.Gallery;
import edu.iot.common.model.GalleryImage;
import edu.iot.common.model.Pagination;

@Service
public class GalleryServiceImpl implements GalleryService {
	
	@Autowired
	GalleryDao dao;
	
	/*@Override
	public Map<String, Object> getPage(int page) throws Exception {
		
		Map<String, Object> map = new HashMap<>();
		
		int total = dao.count();
		Pagination pagination = new Pagination(page, total);
		map.put("pagination", pagination);
		map.put("list", dao.getPage(pagination.getPageMap()));
		
		//기존 getPage를 사용하는 경우
		List<Gallery> list = dao.getPage(pagination.getPageMap());
		for(Gallery g : list) {
			List<GalleryImage> imageList =
					dao.getImages(g.getGalleryId());
			g.setList(imageList);					
		}
		map.put("list", list);
		
		return map;
	}*/
	@Override
	public Map<String, Object> getPage(int page, String orderBy) throws Exception {
		
		Map<String, Object> map = new HashMap<>();
		
		int total = dao.count();
		Pagination pagination = new Pagination(page, total);
		
		
		Map<String, Object> pageMap = pagination.getPageMap();
		pageMap.put("orderBy", orderBy);
		map.put("pagination", pagination);
		map.put("list", dao.getPage(pageMap));
		map.put("orderBy", orderBy);
		
		/*//기존 getPage를 사용하는 경우
		List<Gallery> list = dao.getPage(pagination.getPageMap());
		for(Gallery g : list) {
			List<GalleryImage> imageList =
					dao.getImages(g.getGalleryId());
			g.setList(imageList);					
		}
		map.put("list", list);*/
		
		return map;
	}

	@Override
	public void create(Gallery gallery) throws Exception {
		dao.insert(gallery);
		//log.info(gallery.toString());
				
	}

	@Override
	public Gallery findById(long galleryId) throws Exception {
		dao.increaseReadCnt(galleryId);
		Gallery gallery = dao.findById(galleryId);
		//List<GalleryImage> list = dao.getImages(galleryId);
		//gallery.setList(list); //마이바티스에서 해주기 때문에 리턴만 해주면 된다
		return gallery;
	}
	
	@Override
	public void update(Gallery gallery) throws Exception {
		dao.update(gallery);
		
	}

	@Override
	public void addImage(GalleryImage image) throws Exception {
		dao.addImage(image);
		
	}

	@Override
	public GalleryImage getImage(long imageId) throws Exception {
		return dao.getImage(imageId);
	}	

}
