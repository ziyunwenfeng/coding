package controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//@Controller
//@RequestMapping("/login")
public class Login extends HttpServlet{
	@RequestMapping("/login2")
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(password);
		System.out.println(username);
	}
}
