package emsi.iir4.loka.web.rest;

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
    public Ticket createTicket(@RequestBody Ticket ticket) {
        if (ticket.getId() != null) {
            throw new IllegalArgumentException("invalide id");
        }
        Ticket result = ticketService.create(ticket);
        return (result);
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
    public String getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return "redirect:/tickets";
    }

    @GetMapping("/tickets/{id}")
    public String getTicket(@PathVariable Long id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        return "redirect:/tickets";
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
    public String getDev() {
        List<Ticket> tickets = ticketService.findByDev();
        return "redirect:/tickets";
    }
    @GetMapping("client")
    @PreAuthorize("hasAuthority('ROLE_CLIENT')")
    public String getClient() {
        List<Ticket> tickets = ticketService.findByClient();
        return "redirect:/tickets";
    }

    @GetMapping("/tickets/devNull")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String getDevNull() {
        List<Ticket> tickets = ticketService.findByDevNull();
        return "redirect:/tickets";
    }

}
