package edu.iot.common.test;

import java.util.Map;

import org.junit.Test;

import edu.iot.common.service.BoardService;

public class BoardServiceTest extends BaseServiceTest<BoardService> {

	@Test
	public void testGetPage() throws Exception {
		Map<String, Object> map = service.getPage(1);
		
		assert map.containsKey("pagination"):
				"Pagination 객체가 없습니다.";
		
		assert map.containsKey("list") : "List 객체가 없습니다.";
	}
}
