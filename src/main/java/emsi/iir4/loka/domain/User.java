package emsi.iir4.loka.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A Admin.
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String userName;

    private String password;

    private String Authority;

    @OneToMany
    @JoinColumn(name = "id")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set<Ticket> tickets = new HashSet<>();

    public Long getId() {
        return this.id;
    }

    public User id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public User username(String userName) {
        this.setUserName(userName);
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User password(String password) {
        this.setPassword(password);
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Ticket> getTickets() {
        return this.tickets;
    }

    public void setTickets(Set<Ticket> tickets) {

        this.tickets = tickets;
    }

    public User tickets(Set<Ticket> tickets) {
        this.setTickets(tickets);
        return this;
    }

    public User addTicket(Ticket ticket) {
        this.tickets.add(ticket);
        return this;
    }

    public User removeTicket(Ticket ticket) {
        this.tickets.remove(ticket);
        return this;
    }

    public String getAuthority() {
        return this.Authority;
    }

    public User authority(String authority) {
        this.setAuthority(authority);
        return this;
    }

    public void setAuthority(String authority) {
        this.Authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        return id != null && id.equals(((User) o).id);
    }

    @Override
    public int hashCode() {
        // see
        // https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", username='" + getUsername() + "'" +
                "}";
    }
}
