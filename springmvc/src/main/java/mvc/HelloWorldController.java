package mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {
	@RequestMapping("/hello")
	public String hello(Model model){
		model.addAttribute("greeting", "hello spring mvc");
		return "helloworld";
	}
	@RequestMapping("/login")
	public String login(Model model){
		model.addAttribute("greeting", "hello spring mvc");
		return "login";
	}
}
