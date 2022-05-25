package emsi.iir4.loka.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import emsi.iir4.loka.config.SecurityUtils;
import emsi.iir4.loka.domain.Ticket;
import emsi.iir4.loka.domain.User;
import emsi.iir4.loka.service.TicketService;
import emsi.iir4.loka.service.UserService;
@Controller
@RequestMapping
public class Forward {

	@Autowired
	private UserService userService;
	@Autowired
	private TicketService ticketService;

    @GetMapping("/login")
	String login() {
		return "login";
	}
	//register  user with role 
	@GetMapping("/register")
	String register() {
		return "register";
	}
	//current user
	@GetMapping("/user")
	String user() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	//edit by id
	@GetMapping("/tickets/edit/{id}")
	ModelAndView edit(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView("add_ticket");
		Ticket ticket = ticketService.findById(id);
		mav.addObject("ticket", ticket);
		return mav;
	}
	@GetMapping("/tickets/delete/{id}")
	ModelAndView delete(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView("liste_ticket_client");
		ticketService.delete(id);
		mav.addObject("tickets", ticketService.findAll());
		return mav;
	}
	//authenticate user
	@PostMapping("/authenticate")
	ModelAndView authenticate(String userName, String password) {
	User user = userService.login(userName, password);
	ModelAndView modelAndView = new ModelAndView("redirect:/liste_ticket_"+user.getAuthority().toString().toLowerCase().substring("ROLE_".length()));
	modelAndView.addObject("user", user);
		return modelAndView;
	}
	@GetMapping("/liste_ticket_admin")
	ModelAndView liste_ticket_admin() {
		List<Ticket> tickets = ticketService.findByDevNull();
		ModelAndView modelAndView = new ModelAndView("liste_ticket_admin");
		modelAndView.addObject("tickets", tickets);
		return modelAndView;
	}
	@GetMapping("/liste_ticket_client")
	ModelAndView liste_ticket_client() {
		List<Ticket> tickets = ticketService.findByClient();
		ModelAndView modelAndView = new ModelAndView("liste_ticket_client");
		modelAndView.addObject("tickets", tickets);
		return modelAndView;
	}
	@GetMapping("/liste_ticket_dev")
	ModelAndView liste_ticket_dev() {
		List<Ticket> tickets = ticketService.findByDev();
		ModelAndView modelAndView = new ModelAndView("liste_ticket_dev");
		modelAndView.addObject("tickets", tickets);
		return modelAndView;
	}
	@GetMapping("/tickets/add")
	ModelAndView add() {
		Ticket ticket = new Ticket();
		ModelAndView modelAndView = new ModelAndView("add_ticket");
		modelAndView.addObject("ticket", ticket);
		return modelAndView;
	}
	@GetMapping("/tickets/attribuer/{id}")
	ModelAndView attribuer(@PathVariable Long id) {
		Ticket ticket = ticketService.findById(id);
		ModelAndView modelAndView = new ModelAndView("attribuer");
		modelAndView.addObject("ticket", ticket);
		return modelAndView;
	}
	@GetMapping("/logout")
	String logout() {
		SecurityContextHolder.clearContext();
		return "login";
	}

}
