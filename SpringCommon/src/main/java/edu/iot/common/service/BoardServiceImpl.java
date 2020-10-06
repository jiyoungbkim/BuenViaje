package edu.iot.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iot.common.dao.AttachmentDao;
import edu.iot.common.dao.BoardDao;
import edu.iot.common.exception.PasswordMissmatchException;
import edu.iot.common.model.Attachment;
import edu.iot.common.model.Board;
import edu.iot.common.model.Pagination;
import jdk.nashorn.internal.runtime.ECMAException;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDao dao;
	
	@Autowired
	AttachmentDao attachmentDao;

	@Override
	public Map<String, Object> getPage(int page) throws Exception {

		Map<String, Object> map = new HashMap<>();
		
		int total = dao.count();
		Pagination pagination = new Pagination(page, total);
		map.put("pagination", pagination);
		map.put("list", dao.getPage(pagination.getPageMap()));
		
		return map;
	}

	@Transactional
	@Override
	public void create(Board board) throws Exception {
		//dao.insert(board);
		int result = dao.insert(board);		
		//if(result == 1) { 
			//throw new RuntimeException("test");//에러나면 롤백이 됨
			//throw new Exception("test"); //에러가 나도 롤백 안됨
			//} 
				
		for(Attachment attachment : board.getAttachList()) {
			//attachment.setAttachmentId(14); //무조건 발생 (기본키 제약조건 발생 테스트)
			attachment.setBoardId(board.getBoardId());// 포린키 설정
			attachmentDao.insert(attachment);
		}
		
	}

	@Override
	public Board findById(long boardId) throws Exception {
		dao.increaseReadCnt(boardId);
		Board board = dao.findById(boardId);
		board.setAttachList(attachmentDao.getList(boardId));
		return board;
	}

	@Override
	public Attachment getAttachment(long attachmentId) throws Exception {

		return attachmentDao.findById(attachmentId);
	}
	
	@Transactional
	@Override
	public void update(Board board) throws Exception {
		//패스워드가 틀리면 첨부가 되지 않도록 리턴값 검사
		int result = dao.update(board);
		if(result == 0) {
			throw new PasswordMissmatchException();
		}
		
		for(Attachment attachment : board.getAttachList()) {
			attachment.setBoardId(board.getBoardId());
			attachmentDao.insert(attachment);
		}		
	}


	@Override
	public List<Attachment> getAttachList(long boardId) throws Exception {
		
		return attachmentDao.getList(boardId);
	}

	@Override
	public void deleteAttachment(long boardId, String password, 
								long attachmentId) throws Exception {
		// 게시판의 비밀번호 일치 여부 확인
		// 일치하지 않으면 PasswordMissmatchException 예외 발생
		Board board = dao.findById(boardId);
		if(!password.equals(board.getPassword())) {
			throw new PasswordMissmatchException();
		}
				
		// 일치하면 첨부 파일 삭제
		attachmentDao.delete(attachmentId);
	}
	
	@Transactional
	@Override
	public void delete(Board board) throws Exception {
		//첨부파일 먼저 삭제 - 어떤 문제?
		Board b = dao.findById(board.getBoardId());
		if(!b.getPassword().equals(board.getPassword())) {
			throw new PasswordMissmatchException();
		}
		attachmentDao.deleteByBoardId(board.getBoardId());
		dao.delete(board.getBoardId());
		
	}

	
	
	
}
