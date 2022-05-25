package emsi.iir4.loka.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emsi.iir4.loka.domain.Ticket;
import emsi.iir4.loka.domain.enumeration.Authorities;
import emsi.iir4.loka.domain.enumeration.Cloture;
import emsi.iir4.loka.repository.TicketRepository;
@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserService userService;

    public Ticket create(Ticket ticket) {

        // check if user is a client
        if (!userService.currentUser().getAuthority().equals(Authorities.ROLE_CLIENT)) {
            throw new IllegalArgumentException("invalide user");
        }
        Ticket result = ticketRepository.save(ticket);
        return (result);
    }

    // check if user is an admin and assign ticket to a dev
    public Ticket assign(Long id, String username) {
        Ticket ticket = ticketRepository.findById(id).get();
        System.out.println(id);
        ticket.dev(userService.findByUserName(username)).cloture(Cloture.Ouvert);
        if (!userService.currentUser().getAuthority().equals(Authorities.ROLE_ADMIN)) {
            throw new IllegalArgumentException("invalide user");
        }

        Ticket result = ticketRepository.save(ticket);
        return (result);
    }

    // check if user is dev and close ticket
    public Ticket close(Long id) {
        Ticket ticket = ticketRepository.findById(id).get();
        if (!userService.currentUser().getAuthority().equals(Authorities.ROLE_DEV)) {
            throw new IllegalArgumentException("invalide user");
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
        return ticketRepository.findByDevIdIsNull();
    }
    //findbyid
    public Ticket findById(Long id) {
        return ticketRepository.findById(id).get();
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public void delete(Long id) {
        ticketRepository.deleteById(id);
    }
}
