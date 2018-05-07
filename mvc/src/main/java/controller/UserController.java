package controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import model.User;
import service.IDictService;
import service.IUserService;


@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;
	@Autowired
	private IDictService dictService;

	/*
	 * 用户列表与分页Action
	 */
	@RequestMapping("/list")
	public String list(Model model, @RequestParam(required = false, defaultValue = "1") int pageNO) {
		int size = 5;
		model.addAttribute("size", size);
		model.addAttribute("pageNO", pageNO);
		 model.addAttribute("count",userService.queryUserCount());
		 model.addAttribute("userList", userService.queryUserPager(pageNO,
		 size));
		 System.out.println(pageNO);
		return "user/list";
	}
	@RequestMapping("/add")
	public String add(Model model){
		model.addAttribute("user",new User());
		model.addAttribute("sexList", dictService.getDictByField("SEX"));
		model.addAttribute("eduList", dictService.getDictByField("EDU"));
		return "user/add";
	}
	@RequestMapping("/addSave")
	public String addSave(Model model,@ModelAttribute("user") @Valid User user,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			if(userService.insertUser(user)>0){
				return "redirect:/user/list";
			}
		}
		model.addAttribute("user", user);
		model.addAttribute("sexList",dictService.getDictByField("SEX"));
		model.addAttribute("eduList",dictService.getDictByField("EDU"));
		return "user/add";
	}
	@RequestMapping("edit/{user_id}")
	public String delUser(Model model,@PathVariable int user_id){
		model.addAttribute("user", userService.queryUserById(user_id));
		model.addAttribute("sexList",dictService.getDictByField("SEX"));
		model.addAttribute("eduList",dictService.getDictByField("EDU"));
		return "user/edit";
	}
	@RequestMapping("editSave")
	public String editSave(Model model,@ModelAttribute("user") @Valid User user,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			if(userService.insertUser(user)>0){
				return "redirect:/user/list";
			}
		}
		model.addAttribute("user", user);
		model.addAttribute("sexList",dictService.getDictByField("SEX"));
		model.addAttribute("eduList",dictService.getDictByField("EDU"));
		return "user/edit";
	}
	@RequestMapping("/deleteUserById/{user_id}")
	public String delUser(Model model,@PathVariable int user_id,@RequestParam(required=false,defaultValue="1") int pageNO,
            RedirectAttributes redirectAttributes){
		if(userService.deleteUserById(user_id)>0){
			redirectAttributes.addFlashAttribute("message", "delete");
		}else if(userService.deleteUserById(user_id)>0){
			redirectAttributes.addFlashAttribute("message", "fail delete");
		}
		return "redirect:/user/list?pageNO="+pageNO;
	}
	@RequestMapping("/delUser")
	public String editSave(Model model,@PathVariable int[] user_id,@RequestParam(required=false,defaultValue="1") int pageNO,
            RedirectAttributes redirectAttributes){
		
		if(userService.deleteUsers(user_id)>0){
			redirectAttributes.addFlashAttribute("message", "delete");
		}else if(userService.deleteUsers(user_id)>0){
			redirectAttributes.addFlashAttribute("message", "fail delete");
		}
		return "redirect:/user/list?pageNO="+pageNO;
	}
}
