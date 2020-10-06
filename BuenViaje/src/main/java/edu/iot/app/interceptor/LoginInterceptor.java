package edu.iot.app.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class LoginInterceptor extends BaseInterceptor {

	/*@Autowired
	ServletContext context;*/

	/*@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		//HttpSession session = request.getSession();
		
		//if (session.getAttribute("USER") == null) {
		if (getUser(request) == null) {
			saveUrl("로그인이 필요한 서비스 입니다.", request, response);
			response.sendRedirect(context.getContextPath() + "/account/login");	
		
			return false;
		}
		return super.preHandle(request, response, handler);
	}*/

	@Override
	public String check(HttpServletRequest request) {
		if (getUser(request) == null) {
			return "This service requires LOG IN.";
		} else {
			return null;
		}
	}

/*	public void saveUrl(HttpServletRequest request, HttpServletResponse response) {
		String url = request.getRequestURI().substring(context.getContextPath().length());
		String query = request.getQueryString();
		if (query != null) {
			url = url + "?" + query;
		}
		FlashMap flashMap = new FlashMap();
		flashMap.put("url", url);
		flashMap.put("reason", "로그인이 필요한 서비스 입니다.");
		FlashMapManager flashMapManager = RequestContextUtils.getFlashMapManager(request);
		flashMapManager.saveOutputFlashMap(flashMap, request, response);
	}*/

}
