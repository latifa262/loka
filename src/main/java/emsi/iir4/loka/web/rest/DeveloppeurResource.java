package emsi.iir4.loka.web.rest;

import emsi.iir4.loka.domain.Developpeur;
import emsi.iir4.loka.repository.DeveloppeurRepository;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


/**
 * REST controller for managing {@link emsi.iir4.loka.domain.Developpeur}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DeveloppeurResource {


    private static final String ENTITY_NAME = "ticketDeveloppeur";

    @Value("ticket")
    private String applicationName;

    private final DeveloppeurRepository developpeurRepository;

    public DeveloppeurResource(DeveloppeurRepository developpeurRepository) {
        this.developpeurRepository = developpeurRepository;
    }

    /**
     * {@code POST  /developpeurs} : Create a new developpeur.
     *
     * @param developpeur the developpeur to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new developpeur, or with status {@code 400 (Bad Request)} if the developpeur has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/developpeurs")
    public ResponseEntity<Developpeur> createDeveloppeur(@RequestBody Developpeur developpeur) throws URISyntaxException {
        if (developpeur.getId() != null) {
            //throw new BadRequestAlertException("A new developpeur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Developpeur result = developpeurRepository.save(developpeur);
        return ResponseEntity
            .created(new URI("/api/developpeurs/" + result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /developpeurs/:id} : Updates an existing developpeur.
     *
     * @param id the id of the developpeur to save.
     * @param developpeur the developpeur to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated developpeur,
     * or with status {@code 400 (Bad Request)} if the developpeur is not valid,
     * or with status {@code 500 (Internal Server Error)} if the developpeur couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/developpeurs/{id}")
    public ResponseEntity<Developpeur> updateDeveloppeur(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Developpeur developpeur
    ) throws URISyntaxException {
        if (developpeur.getId() == null) {
            //throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, developpeur.getId())) {
            //throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!developpeurRepository.existsById(id)) {
            //throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Developpeur result = developpeurRepository.save(developpeur);
        return ResponseEntity
            .ok()
            .body(result);
    }

    /**
     * {@code PATCH  /developpeurs/:id} : Partial updates given fields of an existing developpeur, field will ignore if it is null
     *
     * @param id the id of the developpeur to save.
     * @param developpeur the developpeur to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated developpeur,
     * or with status {@code 400 (Bad Request)} if the developpeur is not valid,
     * or with status {@code 404 (Not Found)} if the developpeur is not found,
     * or with status {@code 500 (Internal Server Error)} if the developpeur couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    

    /**
     * {@code GET  /developpeurs} : get all the developpeurs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of developpeurs in body.
     */
    @GetMapping("/developpeurs")
    public List<Developpeur> getAllDeveloppeurs() {
        return developpeurRepository.findAll();
    }

    /**
     * {@code GET  /developpeurs/:id} : get the "id" developpeur.
     *
     * @param id the id of the developpeur to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the developpeur, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/developpeurs/{id}")
    public Developpeur getDeveloppeur(@PathVariable Long id) {
        Optional<Developpeur> developpeur = developpeurRepository.findById(id);
        return developpeur.get();
    }

    /**
     * {@code DELETE  /developpeurs/:id} : delete the "id" developpeur.
     *
     * @param id the id of the developpeur to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/developpeurs/{id}")
    public ResponseEntity<Void> deleteDeveloppeur(@PathVariable Long id) {
        developpeurRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .build();
    }
}
