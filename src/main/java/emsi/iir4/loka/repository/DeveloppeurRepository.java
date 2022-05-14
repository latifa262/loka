package emsi.iir4.loka.repository;

import emsi.iir4.loka.domain.Developpeur;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Developpeur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeveloppeurRepository extends JpaRepository<Developpeur, Long> {}
