import org.springframework.ui.Model;

@Controller
public class HelloWorldController {
	@RequestMapping("/hello")
	public String hello(Model model){
		model.addAttribute("greeting", "hello spring mvc");
		return "hell world";
	}
}
