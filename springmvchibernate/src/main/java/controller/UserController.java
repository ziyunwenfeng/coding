package controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import entity.User;
import service.UserService;
@CrossOrigin("http://localhost:3000")
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value="/manager",method=RequestMethod.GET)
    public ModelAndView hello2(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", "HelloMVC");
        mv.setViewName("user");
        return mv;
    }

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public ModelAndView count() {
		int c = userService.userCount();

		ModelAndView mv = new ModelAndView();
		mv.addObject("message", c);
		mv.setViewName("user");
		return mv;
	}
	
	//Restful
	@RequestMapping(value = "/users",method = RequestMethod.GET)
	@ResponseBody
	public List<User> getUsers(){
		List<User> users = new LinkedList<User>();
		User tang = new User();
		
		tang.setName("tang");
		tang.setPwd("123");
		tang.setId(1);
		User zhano = new User();
		zhano.setName("tang");
		zhano.setPwd("123");
		zhano.setId(2);
		User iou = new User();
		iou.setName("tang");
		iou.setPwd("123");
		iou.setId(3);
		users.add(tang);
		users.add(zhano);
		users.add(iou);
		return users;
	}
	@RequestMapping(value = "/aaa",method = RequestMethod.GET)
	public String  getUsers222(){
		return "index";
	}
	
	@RequestMapping(value = "/saveU",method = RequestMethod.POST)
	@ResponseBody
	public void saveUser(@RequestBody User u){
		System.out.println("saveU"+u.getName()+"***"+u.getPwd()+ "***"+u.getId());
	}
	
	@RequestMapping(value = "/getUser",method = RequestMethod.GET)
	@ResponseBody
	public List<User> getUser(@RequestParam("id") String id){
		System.out.println("getUser:"+id);
//		return  userService.getById(id);
		List<User> users = new LinkedList<User>();
		User zhano = new User();
		zhano.setName("tang");
		zhano.setPwd("123");
		zhano.setId(2);
		users.add(zhano);
		return users;
	}
	
	@RequestMapping(value = "/saveUs",method = RequestMethod.POST)
	@ResponseBody
	public void saveUser(@RequestBody List<User> users){
		System.out.println(")))))"+users.size());
	}
	@RequestMapping(value = "/ss",method = RequestMethod.POST)
	@ResponseBody
	public void testAjax(){
		System.out.println("ajax-------");
	}
	@CrossOrigin("http://localhost:3000")
	@RequestMapping(value = "/abc",method = RequestMethod.GET)
	@ResponseBody
	public String testAjax2(){
		System.out.println("ajax!!!!!!!");
		return "ajax-------";
	}
}
