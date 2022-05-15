package emsi.iir4.loka.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;


/**
 * An authority (a securiy role) used by Spring Security.
 */
@Entity
public class Authorities implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    private String name;
    @OneToOne
    private User user;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Authorities)) {
            return false;
        }
        return Objects.equals(name, ((Authorities) o).name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Authority{" +
            "name='" + name + '\'' +
            "}";
    }
}
