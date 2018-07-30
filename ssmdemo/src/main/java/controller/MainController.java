package controller;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.Article;
import entity.Author;
import entity.Comments;
import entity.Stu;
import service.AuthorService;
import service.CommentsService;
import service.IArticleService;
import service.IStuService;
import utils.JWTUtil;
import utils.ResponseData;
//@CrossOrigin("http://localhost:3000")
//@Controller
//@RequestMapping("/user")
public class MainController {
	@Autowired
	private IStuService service;
	@Autowired
	private IArticleService articleService;
	@Autowired
	private AuthorService authorService;
	@Autowired
	private CommentsService commentsService;
	@RequestMapping(value = "/users",method = RequestMethod.GET)
	@ResponseBody
	public List<Stu> getUsers(){
		List<Stu> users = new LinkedList<Stu>();
		Stu tang = new Stu();
		
		tang.setName("tang");
		tang.setAge((short)11);
		Stu zhano = new Stu();
		zhano.setName("tang");
		zhano.setAge((short)11);
		
		Stu iou = new Stu();
		iou.setName("tang");
		iou.setAge((short)11);
		
		users.add(tang);
		users.add(zhano);
		users.add(iou);
		return users;
	}
	@RequestMapping(value = "/insert",method = RequestMethod.POST)
	@ResponseBody
	public void insertStu(@RequestBody Stu stu){
		service.insertStu(stu);
	}
	@RequestMapping(value = "/select",method = RequestMethod.GET)
	@ResponseBody
	public Stu getStu(@RequestParam("name") String name){
		return service.getByName(name);
	}
	@RequestMapping(value = "/all",method = RequestMethod.GET)
	@ResponseBody
	public List<Stu> getAllStu(){
		return service.getAll();
	}
	@RequestMapping(value = "/selectArticle",method = RequestMethod.GET)
	@ResponseBody
	public Article getArticle(@RequestParam("articleId") Long articleId){
		return articleService.selectArticleByArticle(articleId);
	}
	@RequestMapping(value = "/selectArticleComments",method = RequestMethod.GET)
	@ResponseBody
	public Article getArticleComments(@RequestParam("articleId") Long articleId){
		return articleService.selectArticleWithCommentsByArticle(articleId);
	}
	@RequestMapping(value = "/updateArticleComments",method = RequestMethod.POST)
	@ResponseBody
	public void updateArticleComments(@RequestBody Article article){
		articleService.updateArticle(article);
	}
	@RequestMapping(value = "/selectArticleByComments",method = RequestMethod.GET)
	@ResponseBody
	public Comments getArticleByComments(@RequestParam("commentsId") Long commentsId){
		return articleService.selectArticleByComments(commentsId);
	}
	
	@RequestMapping(value = "/insertArticle",method = RequestMethod.POST)
	@ResponseBody
	public void insertArticle(@RequestBody Article article){
		articleService.insertArticle(article);
	}
	@RequestMapping(value = "/deleteArticle",method = RequestMethod.POST)
	@ResponseBody
	public void deleteArticle(@RequestParam("articleId") Long articleId){
		articleService.deleteArticle(articleId);
	}
	@RequestMapping(value = "/insertAuthor",method = RequestMethod.POST)
	@ResponseBody
	public void insertAuthor(@RequestBody Author author){
		authorService.insertAuthor(author);
	}
	@RequestMapping(value = "/deleteAuthor",method = RequestMethod.POST)
	@ResponseBody
	public void deleteAuthor(@RequestParam("authorId") Long authorId){
		authorService.deleteAuthor(authorId);
	}
	@RequestMapping(value = "/selectComments",method = RequestMethod.GET)
	@ResponseBody
	public Comments getComments(@RequestParam("commentsId") Long commentsId){
		return commentsService.getComments(commentsId);
	}
	@RequestMapping(value = "/updateComments",method = RequestMethod.POST)
	@ResponseBody
	public void updateComments(@RequestBody Comments comments){
		 commentsService.updateComments(comments);
	}
	@RequestMapping(value = "/httpRequest",method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getRequest(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", request.hashCode());
		return map;
	}
	@RequestMapping(value = "/http",method = RequestMethod.GET)
	@ResponseBody
	public void getRequest(HttpServletRequest request, HttpServletResponse resp,HttpSession session){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", request.hashCode());
		HttpSession session1 = request.getSession();
		String key = "test_session";
		session1.setAttribute(key, new Date());
		Date time = (Date)session1.getAttribute(key);
		String sessionId = session1.getId();
		if(session1.isNew()) {
			System.out.println("new");
			System.out.println(sessionId);
		}
		else {
			System.out.println("old");
			System.out.println(sessionId);
		}
	}
	
	@RequestMapping(value = "/jwt",method = RequestMethod.POST)
	@ResponseBody
	public void jwt(@RequestBody Stu stu){
		
		String token = JWTUtil.encode(stu, 10000);
		System.out.println("token");
		System.out.println(token);
		Stu s = (Stu)JWTUtil.decode(token, Stu.class);
		System.out.println("stu");
		System.out.println(stu.getName());
	}
	@RequestMapping(value = "/jwtTest",method = RequestMethod.POST)
	@ResponseBody
	public ResponseData jwtTest(@RequestBody Stu stu,HttpServletRequest request) {
		System.out.println("header");
		System.out.println(request.getHeaders("token"));
		if("tangwenfeng".equals(stu.getName())&&15==stu.getAge()) {
			ResponseData responseData = ResponseData.ok();
			String token = JWTUtil.encode(stu, 10000000);
			System.out.println("token");
			System.out.println(token);
			Stu s = (Stu)JWTUtil.decode(token, Stu.class);
			System.out.println("stu");
			System.out.println(s.getName());
			
			responseData.putData(token, "token");
			
			return responseData;
		}
		else
			return ResponseData.customerError().putData(ResponseData.ERROR,"用户名或者密码错误");
	}
	
}
