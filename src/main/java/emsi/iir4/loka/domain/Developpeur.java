package emsi.iir4.loka.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A Developpeur.
 */
@Entity
@Table(name = "developpeur")
public class Developpeur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom")
    private String nom;

    @OneToMany(mappedBy = "developpeur")
    @JsonIgnoreProperties(value = { "client", "developpeur", "admin" }, allowSetters = true)
    private Set<Ticket> tickets = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Developpeur id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public Developpeur nom(String nom) {
        this.setNom(nom);
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Ticket> getTickets() {
        return this.tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        if (this.tickets != null) {
            this.tickets.forEach(i -> i.setDeveloppeur(null));
        }
        if (tickets != null) {
            tickets.forEach(i -> i.setDeveloppeur(this));
        }
        this.tickets = tickets;
    }

    public Developpeur tickets(Set<Ticket> tickets) {
        this.setTickets(tickets);
        return this;
    }

    public Developpeur addTicket(Ticket ticket) {
        this.tickets.add(ticket);
        ticket.setDeveloppeur(this);
        return this;
    }

    public Developpeur removeTicket(Ticket ticket) {
        this.tickets.remove(ticket);
        ticket.setDeveloppeur(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Developpeur)) {
            return false;
        }
        return id != null && id.equals(((Developpeur) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Developpeur{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            "}";
    }
}
