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
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "urgence")
    private Urgence urgence;

    @Enumerated(EnumType.STRING)
    @Column(name = "cloture")
    private Cloture cloture;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "env_ex")
    private String envEx;

    @Column(name = "logiciel")
    private String logiciel;

    @ManyToOne
    @JsonIgnoreProperties(value = { "tickets" }, allowSetters = true)
    private Client client;

    @ManyToOne
    @JsonIgnoreProperties(value = { "tickets" }, allowSetters = true)
    private Developpeur developpeur;

    @ManyToOne
    @JsonIgnoreProperties(value = { "tickets" }, allowSetters = true)
    private Admin admin;

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

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Ticket client(Client client) {
        this.setClient(client);
        return this;
    }

    public Developpeur getDeveloppeur() {
        return this.developpeur;
    }

    public void setDeveloppeur(Developpeur developpeur) {
        this.developpeur = developpeur;
    }

    public Ticket developpeur(Developpeur developpeur) {
        this.setDeveloppeur(developpeur);
        return this;
    }

    public Admin getAdmin() {
        return this.admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Ticket admin(Admin admin) {
        this.setAdmin(admin);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

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
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
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
