package controller;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import service.IArticleService;
import service.IStuService;
@Controller
@RequestMapping("/user")
public class MainController {
	@Autowired
	private IStuService service;
	@Autowired
	private IArticleService articleService;
	@Autowired
	private AuthorService authorService;
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
}
