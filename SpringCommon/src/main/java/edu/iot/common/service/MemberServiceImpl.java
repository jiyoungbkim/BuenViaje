package edu.iot.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.iot.commom.sec.SHA256Util;
import edu.iot.common.dao.AvataDao;
import edu.iot.common.dao.MemberDao;
import edu.iot.common.exception.LoginFailException;
import edu.iot.common.exception.PasswordMissmatchException;
import edu.iot.common.model.Avata;
import edu.iot.common.model.Member;
import edu.iot.common.model.Pagination;
import edu.iot.common.model.Search;
import edu.iot.common.util.ImageUtil;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao dao;

	@Autowired
	AvataDao avataDao;
	
	byte[] anonyAvata;
	
	@PostConstruct
	public void setup() {
		Avata avata;
		try {
			avata = avataDao.findById("anonymous");
			anonyAvata = avata.getImage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Member login(String userId, String password) throws Exception {

		Member member = dao.findById(userId);// 회원정보가 들어있는 멤버인스턴스를 받아서
		System.out.println(member);
		if (member == null) {
			throw new LoginFailException("사용자 ID가 존재하지 않습니다.");

		} else {
			String encPassword = SHA256Util.getEncrypt(password, member.getSalt());

			if (encPassword.equals(member.getPassword())) {
				return member;// 리턴
			} else {
				throw new LoginFailException("사용자 ID 또는 비밀번호가 일치하지 않습니다.");

			}
		}
	}

	@Override
	public void join(Member member) throws Exception {

		String salt = SHA256Util.generateSalt();
		String encPassword = SHA256Util.getEncrypt(member.getPassword(), salt);
		member.setSalt(salt);
		member.setPassword(encPassword);

		dao.insert(member);
	}

	@Override
	public String idcheck(String userId) throws Exception {

		Member member = dao.findById(userId);
		if (member == null) {
			return "success";
		} else {
			return "fail";
		}
	}

	@Override
	// public List<Member> getPage(int page) throws Exception {
	/*
	 * public Map<String,Object> getPage(int page) throws Exception {
	 * Map<String,Object> map = new HashMap<>();
	 * 
	 * int total = dao.count(); int totalPage = (int)Math.ceil(total/10.);
	 * 
	 * int start = (page-1)*10 + 1; int end = start +9; Map<String,Object> pageMap =
	 * new HashMap<>(); pageMap.put("start", start); pageMap.put("end", end);
	 * 
	 * map.put("page", page); map.put("total", total); map.put("totalPage",
	 * totalPage); map.put("list", dao.getPage(pageMap));
	 * 
	 * return map; }
	 */
	public Map<String, Object> getPage(int page) throws Exception {
		Map<String, Object> map = new HashMap<>();

		int total = dao.count();
		Pagination pagination = new Pagination(page, total);
		map.put("pagination", pagination);
		map.put("list", dao.getPage(pagination.getPageMap()));

		return map;
	}

	@Override
	public Member findById(String userId) throws Exception {
		return dao.findById(userId);
	}

	@Override
	public void delete(String userId) throws Exception {
		dao.delete(userId);
	}

	@Override
	public int update(Member member, Member sMember) throws Exception {
		// member.setUserId(sMember.getUserId()); update.jsp에서 히든 처리 해줘서 필요없다

		String salt = sMember.getSalt(); // sMember 세션에 저장되어있는 비밀번호 암호화
		String encPassword = SHA256Util.getEncrypt(member.getPassword(), salt);
		
		member.setPassword(encPassword);

		/*return dao.update(member);*/
		if(dao.update(member)==0)
			throw new PasswordMissmatchException();
		return 1;
	}

	@Override
	public int changePassword(Member member, String oldPassword, String newPassword) throws Exception {
		Map<String, String> map = new HashMap<>();
		String salt = member.getSalt();
		String encOldPassword = SHA256Util.getEncrypt(oldPassword, salt);
		String encNewPassword = SHA256Util.getEncrypt(newPassword, salt);

		map.put("userId", member.getUserId());
		map.put("oldPassword", encOldPassword);
		map.put("newPassword", encNewPassword);

		return dao.changePassword(map);
	}

	@Override
	public String list(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByAdmin(Member member) throws Exception {
		return dao.updateByAdmin(member);
	}

	@Override
	public List<Member> search(Search search) throws Exception {
		return dao.search(search);
	}
		
	
	@Override
	public byte[] getAvata(String userId) throws Exception {
		Avata avata = avataDao.findById(userId);
		if (avata == null) { //아바타 이미지가 없는 경우
			//avata = avataDao.findById("anonymous");
			return anonyAvata;
		}
		return avata.getImage();
	}

	@Override
	public void insertAvata(Avata avata) throws Exception {
		avata.setImage(ImageUtil.makeThumb(avata.getImage()));
		avataDao.insert(avata);
	}

	@Override
	public void updateAvata(Avata avata) throws Exception {
		avata.setImage(ImageUtil.makeThumb(avata.getImage())); 
		
		if(avataDao.update(avata) == 0) {
			avataDao.insert(avata);
		}
	}

	@Override
	public void deleteAvata(String userId) throws Exception {
		avataDao.delete(userId);

	}

}
