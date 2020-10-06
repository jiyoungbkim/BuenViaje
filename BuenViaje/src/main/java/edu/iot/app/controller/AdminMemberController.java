package edu.iot.app.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.iot.commom.sec.SHA256Util;
import edu.iot.common.model.Member;
import edu.iot.common.model.Search;
import edu.iot.common.model.UserLevel;
import edu.iot.common.service.MemberService;
import edu.iot.common.util.Util;

@Controller
@RequestMapping("/admin/member")
public class AdminMemberController {
	
	@Autowired
	MemberService service; 
	
	@RequestMapping(value="/list")
	public void list(
			@RequestParam(value="page", defaultValue="1") int page,
			Model model) throws Exception {
		
		
		model.addAllAttributes(service.getPage(page));
		model.addAttribute("today", Util.getToday());
	}
	
	@RequestMapping(value="/view/{userId}", method=RequestMethod.GET)
	public String view(@PathVariable String userId, Model model) 
										throws Exception {
		
		Member member = service.findById(userId);
		model.addAttribute("member", member);
		return "admin/member/view";
	}
	
	@RequestMapping(value="/edit/{userId}", method=RequestMethod.GET)
	public String editForm(@PathVariable String userId, Model model)
										throws Exception {
		Member member = service.findById(userId);
		model.addAttribute("userLevels", UserLevel.values());
		model.addAttribute("member", member);
		return "admin/member/edit";
	}
	
	@RequestMapping(value="/edit/{userId}", method=RequestMethod.POST)
	public String editSubmit(
			@Valid Member member,
			BindingResult result,// 순서가 안맞으면 400에러
			HttpSession session,
			@RequestParam(value="page", defaultValue="1") int page,
			Model model) throws Exception {
		
		if(result.hasErrors()) { // 유효성 검사			
			model.addAttribute("userLevels", UserLevel.values());
			return "admin/member/edit"; // 뷰의 이름
		}
		Member admin = (Member)session.getAttribute("USER");
		String encPassword = SHA256Util.getEncrypt(
				member.getPassword(), admin.getSalt());
		
		if(encPassword.equals(admin.getPassword())) {
			service.updateByAdmin(member);
		} else {
			model.addAttribute("userLevels", UserLevel.values());
			result.reject("fail","비밀번호가 일치하지 않습니다."); //전역 에러
			return "admin/member/edit";
		}		
		
		return "redirect:/admin/member/view/" + 
					member.getUserId() + "?page="+page; // url
		
	}
	
	@RequestMapping(value="/delete/{userId}", method=RequestMethod.GET)
	public String delete(@PathVariable String userId) throws Exception {
		service.delete(userId);
		return "redirect:/admin/member/list";
	}
	
	@RequestMapping("/search")
	public void search(Search search, Model model) throws Exception {
		model.addAttribute("today", Util.getToday());
		model.addAttribute("list", service.search(search));
	}
}
