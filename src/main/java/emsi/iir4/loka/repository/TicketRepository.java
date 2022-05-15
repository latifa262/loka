package emsi.iir4.loka.repository;

import emsi.iir4.loka.domain.Ticket;
import emsi.iir4.loka.domain.User;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data SQL repository for the Ticket entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByDevUserNameContaining(String devUserName);
    List<Ticket> findByClientUserNameContaining(String clientUserName);
    List<Ticket> findByDevNull();
    List<Ticket> findByClient(User currentUser);
    List<Ticket> findByDev(User currentUser);
    
}
