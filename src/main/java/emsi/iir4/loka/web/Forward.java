package emsi.iir4.loka.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import emsi.iir4.loka.domain.User;
import emsi.iir4.loka.service.UserService;
@Controller
public class Forward {

	@Autowired
	private UserService userService;


    @GetMapping("/login")
	String login() {
		return "login";
	}
	//register  user with role 
	@PostMapping("/register")
	String register(User user ) {
		userService.create(user);
		return "login";
	}
	//authenticate user
	@PostMapping("/authenticate")

	ModelAndView authenticate(String userName, String password) {
	User user = userService.login(userName, password);
	ModelAndView modelAndView = new ModelAndView("redirect:/index");
	modelAndView.addObject("user", user);
		return modelAndView;
	}
	
	
}
