package emsi.iir4.loka.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import emsi.iir4.loka.domain.Ticket;
import emsi.iir4.loka.domain.enumeration.Cloture;
import emsi.iir4.loka.repository.TicketRepository;

public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserService userService;

    public Ticket create(Ticket ticket) {
        if (ticket.getId() != null) {
            throw new IllegalArgumentException("invalide id");
        }
        // check if user is a client
        if (!userService.currentUser().getAuthority().equals("ROLE_CLIENT")) {
            throw new IllegalArgumentException("invalide user");
        }
        ticket.client(userService.currentUser()).cloture(Cloture.Ouvert);
        Ticket result = ticketRepository.save(ticket);
        return (result);
    }

    // check if user is an admin and assign ticket to a dev
    public Ticket assign(Long id) {
        Ticket ticket = ticketRepository.findById(id).get();
        if (!userService.currentUser().getAuthority().equals("ROLE_ADMIN")) {
            throw new IllegalArgumentException("invalide user");
        }
        // check if dev has dev authority
        if (!ticket.getDev().getAuthority().equals("ROLE_DEV")) {
            throw new IllegalArgumentException("invalide dev");
        }
        Ticket result = ticketRepository.save(ticket);
        return (result);
    }

    // check if user is dev and close ticket
    public Ticket close(Long id) {
        Ticket ticket = ticketRepository.findById(id).get();
        if (!userService.currentUser().getAuthority().equals("ROLE_DEV")) {
            throw new IllegalArgumentException("invalide user");
        }
        // check if dev has dev authority
        if (!ticket.getDev().getAuthority().equals("ROLE_DEV")) {
            throw new IllegalArgumentException("invalide dev");
        }
        ticket.cloture(Cloture.Resolu);
        Ticket result = ticketRepository.save(ticket);
        return (result);
    }

    // find tickets where dev is current user
    public List<Ticket> findByDev() {
        return ticketRepository.findByDev(userService.currentUser());
    }
    // find tickets where client is current user
    public List<Ticket> findByClient() {
        return ticketRepository.findByClient(userService.currentUser());
    }
    //find tickets where dev is null
    public List<Ticket> findByDevNull() {
        return ticketRepository.findByDevNull();
    }
}
