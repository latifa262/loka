package emsi.iir4.loka.web.controller;

import emsi.iir4.loka.domain.Ticket;
import emsi.iir4.loka.domain.enumeration.Cloture;
import emsi.iir4.loka.repository.TicketRepository;
import emsi.iir4.loka.service.TicketService;
import emsi.iir4.loka.service.UserService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/* 
Cette classe est un controleur qui contient des méthodes pas utilisées dans les interfaces web.
les methodes utiles sont dans le controller Forward.java
*/
@Controller
@RequestMapping("/api")
@Transactional
public class TicketResource {

    private static final String ENTITY_NAME = "ticketTicket";

    @Value("ticket")
    private String applicationName;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private UserService userService;

    @PostMapping("/tickets")
    public ModelAndView createTicket(@ModelAttribute("ticket") Ticket ticket) {

        ticket.setClient(userService.currentUser());
        Ticket result = ticketService.create(ticket);
        ModelAndView modelAndView = new ModelAndView("redirect:/liste_ticket_client");
        modelAndView.addObject("ticket", result);

        return modelAndView;
    }

    @PostMapping("/tickets/edit")
    public String updateTicket(@ModelAttribute Ticket ticket ) {
        Ticket result = ticketRepository.save(ticket);
        String authority = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString()
                .toLowerCase().substring("ROLE_".length());
        return "redirect:/liste_ticket_" + authority;

    }

    @PostMapping("/tickets/close/{id}")
    public void closeTicket(@PathVariable Long id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent()) {
            ticket.get().setCloture(Cloture.Resolu);
            ticketRepository.save(ticket.get());
        }
    }

    // les méthodes ci dessous ne sont pas utilisées dans les interfaces

    @GetMapping("/tickets")
    public ModelAndView getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("redirect:/tickets");
        modelAndView.addObject("tickets", tickets);
        return modelAndView;
    }

    @GetMapping("/tickets/{id}")
    public ModelAndView getTicket(@PathVariable Long id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/tickets");
        modelAndView.addObject("ticket", ticket);
        return modelAndView;
    }

    @DeleteMapping("/tickets/{id}")
    public String deleteTicket(@PathVariable Long id) {
        ticketRepository.deleteById(id);

        return "redirect:/tickets";
    }

    @PostMapping("/tickets/assign")
    public ModelAndView assign(@RequestParam String id ,@RequestParam String dev) {
        Long idTicket = Long.parseLong(id);
        ticketService.assign(idTicket,dev);
        ModelAndView modelAndView = new ModelAndView("redirect:/liste_ticket_admin");
        modelAndView.addObject("tickets", ticketRepository.findByDevIdIsNull());
        return  modelAndView;
    }

    @GetMapping("dev")
    @PreAuthorize("hasAuthority('ROLE_DEV')")
    public ModelAndView getDev() {
        List<Ticket> tickets = ticketService.findByDev();
        ModelAndView modelAndView = new ModelAndView("redirect:/tickets");
        modelAndView.addObject("tickets", tickets);
        return modelAndView;
    }

    @GetMapping("client")
    @PreAuthorize("hasAuthority('ROLE_CLIENT')")
    public ModelAndView getClient() {
        List<Ticket> tickets = ticketService.findByClient();
        ModelAndView modelAndView = new ModelAndView("redirect:/tickets");
        modelAndView.addObject("tickets", tickets);
        return modelAndView;
    }

    @GetMapping("admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ModelAndView getDevNull() {
        List<Ticket> tickets = ticketService.findByDevNull();
        ModelAndView modelAndView = new ModelAndView("redirect:/tickets");
        modelAndView.addObject("tickets", tickets);
        return modelAndView;
    }

}
