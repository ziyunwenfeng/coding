package controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("hello");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(password);
		System.out.println(username);
		HttpSession session = request.getSession();
		try {
			System.out.println("path"+request.getContextPath());
			if(username.equals("wenfeng")&&password.equals("123456")) {
				session.setAttribute("password", password);
				session.setAttribute("username", username);
				response.sendRedirect(request.getContextPath()+"/IndexServlet");
			}else {
				response.sendRedirect(request.getContextPath()+"/fail.html");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) {
		doGet(request,response);
	}
}

