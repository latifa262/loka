package emsi.iir4.loka.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import emsi.iir4.loka.domain.enumeration.Cloture;
import emsi.iir4.loka.domain.enumeration.Urgence;
import java.io.Serializable;
import javax.persistence.*;

/**
 * A Ticket.
 */
@Entity
@Table(name = "ticket")
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Urgence urgence;

    @Enumerated(EnumType.STRING)
    private Cloture cloture;

    @Lob
    private String description;

    private String envEx;

    private String logiciel;

    @ManyToOne
    @JsonIgnoreProperties(value = { "tickets" }, allowSetters = true)
    private User admin;
    @ManyToOne
    @JsonIgnoreProperties(value = { "tickets" }, allowSetters = true)
    private User dev;
    @ManyToOne
    @JsonIgnoreProperties(value = { "tickets" }, allowSetters = true)
    private User client;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Ticket id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Urgence getUrgence() {
        return this.urgence;
    }

    public Ticket urgence(Urgence urgence) {
        this.setUrgence(urgence);
        return this;
    }

    public void setUrgence(Urgence urgence) {
        this.urgence = urgence;
    }

    public Cloture getCloture() {
        return this.cloture;
    }

    public Ticket cloture(Cloture cloture) {
        this.setCloture(cloture);
        return this;
    }

    public void setCloture(Cloture cloture) {
        this.cloture = cloture;
    }

    public String getDescription() {
        return this.description;
    }

    public Ticket description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEnvEx() {
        return this.envEx;
    }

    public Ticket envEx(String envEx) {
        this.setEnvEx(envEx);
        return this;
    }

    public void setEnvEx(String envEx) {
        this.envEx = envEx;
    }

    public String getLogiciel() {
        return this.logiciel;
    }

    public Ticket logiciel(String logiciel) {
        this.setLogiciel(logiciel);
        return this;
    }

    public void setLogiciel(String logiciel) {
        this.logiciel = logiciel;
    }

    public User getAdmin() {
        return this.admin;
    }

    public Ticket admin(User user) {
        this.setAdmin(user);
        return this;
    }

    public void setAdmin(User user) {
        this.admin = user;
    }

    public User getDev() {
        return this.dev;
    }

    public Ticket dev(User user) {
        this.setDev(user);
        return this;
    }

    public void setDev(User user) {
        this.dev = user;
    }

    public User getClient() {
        return this.client;
    }

    public Ticket client(User user) {
        this.setClient(user);
        return this;
    }

    public void setClient(User user) {
        this.client = user;
    }
    

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ticket)) {
            return false;
        }
        return id != null && id.equals(((Ticket) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + getId() +
                ", urgence='" + getUrgence() + "'" +
                ", cloture='" + getCloture() + "'" +
                ", description='" + getDescription() + "'" +
                ", envEx='" + getEnvEx() + "'" +
                ", logiciel='" + getLogiciel() + "'" +
                "}";
    }
}
