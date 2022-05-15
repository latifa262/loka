package emsi.iir4.loka.web.controller;

import emsi.iir4.loka.domain.Ticket;
import emsi.iir4.loka.repository.TicketRepository;
import emsi.iir4.loka.service.TicketService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @PostMapping("/tickets")
    public ModelAndView createTicket(@RequestBody Ticket ticket) {
        if (ticket.getId() != null) {
            throw new IllegalArgumentException("invalide id");
        }
        Ticket result = ticketService.create(ticket);
        ModelAndView modelAndView = new ModelAndView("redirect:/tickets");
        modelAndView.addObject("ticket", result);
        return modelAndView;
    }

    @PutMapping("/tickets")
    public String updateTicket(@RequestBody Ticket ticket) {
        if (ticket.getId() == null) {
            throw new IllegalArgumentException("invalide id");
        }
        Ticket result = ticketRepository.save(ticket);
        return "redirect:/tickets";

    }

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

    @PutMapping("/tickets/{id}")
    public String assign(@PathVariable Long id) {
        ticketService.assign(id);
        return "redirect:/tickets";
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

    @GetMapping("/tickets/devNull")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ModelAndView getDevNull() {
        List<Ticket> tickets = ticketService.findByDevNull();
        ModelAndView modelAndView = new ModelAndView("redirect:/tickets");
        modelAndView.addObject("tickets", tickets);
        return modelAndView;
    }

}
