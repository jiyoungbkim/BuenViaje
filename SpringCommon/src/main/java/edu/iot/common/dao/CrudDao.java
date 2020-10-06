package edu.iot.common.dao;

import java.util.List;
import java.util.Map;

import edu.iot.common.model.Board;

public interface CrudDao<M, K> {
	int count() throws Exception; //총 개수	
	
	M findById(K k) throws Exception; //키하나 찾아서 리턴 (없으면 널)
	
	List<M> getList() throws Exception; //전체 리스트 찾아서 리턴 (없으면 빈 리스트)
	
	int insert(M m) throws Exception; //int 영향받은 레코드의 개수 
	
	int update(M m) throws Exception;
	
	int delete(K k) throws Exception;
	
	List<M> getPage(Map map) throws Exception; //페이지네이션
	
	List<M> random(int num) throws Exception; //랜덤
}
