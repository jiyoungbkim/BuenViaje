package edu.iot.common.dao;

import java.util.List;

import edu.iot.common.model.Attachment;

public interface AttachmentDao {
	
	void insert(Attachment attachment) throws Exception;// 리턴 타입 인테저와 보이드가 가능

	List<Attachment> getList(long boardId) throws Exception;
	
	Attachment findById(long attachmentId) throws Exception;
	
	void delete(long attachmentId) throws Exception;

	void deleteByBoardId(long boardId) throws Exception;
	
}
