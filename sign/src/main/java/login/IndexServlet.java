package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class IndexServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) {
		doGet(request,response);
	}
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession(false);
//		Cookie[] cookies = request.getCookies();
//		if(cookies!=null) {
//			System.out.println("cookie not null");
//			for(int i=0;i<cookies.length;i++) {
//				Cookie cookie = cookies[i];
//				System.out.println(cookie.getName());
//				if(cookie.getName().equals("lastAccessTime")) {
//					Long lastAccessTime = Long.parseLong(cookie.getValue());
//					Date date = new Date(lastAccessTime);
//					System.out.println(date.toString());
//				}
//			}
//		}else {
//			System.out.println("first time login");
//		}
//		Cookie cookie = new Cookie("lastAccessTime",System.currentTimeMillis()+"");
//		cookie.setMaxAge(60*60);
//		response.addCookie(cookie);
		String html= "";
		try {
			PrintWriter writer = response.getWriter();
			if(session==null) {
				response.sendRedirect(request.getContextPath()+"/index.html");
			}else {
				String username = (String)session.getAttribute("username");
				if(username==null) {
					response.sendRedirect(request.getContextPath()+"/index.html");
				}else {
					System.out.println("*******"+username);
					html = "<html><body>欢迎回来，" +username+ "</a></body></html>";
					writer.write(html);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
