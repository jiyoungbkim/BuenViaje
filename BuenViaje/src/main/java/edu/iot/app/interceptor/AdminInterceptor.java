package edu.iot.app.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class AdminInterceptor extends BaseInterceptor {

	/*@Autowired
	ServletContext context;*/

	/*@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		//HttpSession session = request.getSession();
		//Member member = (Member) session.getAttribute("USER");

		//if (member.getUserLevel() != UserLevel.ADMIN) {
		if (!isAdmin(request)) {
			saveUrl("관리자 권한이 필요합니다.", request, response);
			response.sendRedirect(context.getContextPath() + "/account/login");
			return false;
		}
		return super.preHandle(request, response, handler);
	}*/

	@Override
	public String check(HttpServletRequest request) {
		if(!isAdmin(request)) {
			return "관리자 권한이 필요합니다.";
		}
		return null;
	}

}


