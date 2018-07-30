package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Stu;
import utils.JWTUtil;
//@WebFilter(urlPatterns="/user/*")
public class TokenFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		System.out.println("filter");
		if(request.getRequestURI().startsWith("/user/jwtTest")) {
			
			System.out.println("no filter");
			chain.doFilter(request,response);
		}
		else {
			String token = request.getHeader("token");
			System.out.println("in the filter");
			System.out.println(token);
			if(JWTUtil.checkToken(token)) {
				Stu s = (Stu)JWTUtil.decode(token, Stu.class);
				request.setAttribute("user", s);
				chain.doFilter(request, response);
			}else {
				System.out.println("无效token");
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	
}
