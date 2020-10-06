package edu.iot.common.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDaoTest<D> extends BaseTest {
	@Autowired
	D dao;
	
	//오토와이어드가 제대로 됐나 테스트
	@Test
	public void test() {
		assert dao!= null : //이 조건이 참이면 통과
			dao.getClass().getSimpleName() + " 바인딩 실패"; //거짓이면 예외 메시지 발생
	}
}
