package emsi.iir4.loka.web.rest;

import emsi.iir4.loka.domain.Admin;
import emsi.iir4.loka.repository.AdminRepository;
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
 * REST controller for managing {@link emsi.iir4.loka.domain.Admin}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AdminResource {


    private static final String ENTITY_NAME = "ticketAdmin";

    @Value("ticket")
    private String applicationName;

    private final AdminRepository adminRepository;

    public AdminResource(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    /**
     * {@code POST  /admins} : Create a new admin.
     *
     * @param admin the admin to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admin, or with status {@code 400 (Bad Request)} if the admin has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/admins")
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) throws URISyntaxException {
        if (admin.getId() != null) {
            //throw new BadRequestAlertException("A new admin cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Admin result = adminRepository.save(admin);
        return ResponseEntity
            .created(new URI("/api/admins/" + result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /admins/:id} : Updates an existing admin.
     *
     * @param id the id of the admin to save.
     * @param admin the admin to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admin,
     * or with status {@code 400 (Bad Request)} if the admin is not valid,
     * or with status {@code 500 (Internal Server Error)} if the admin couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/admins/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable(value = "id", required = false) final Long id, @RequestBody Admin admin)
        throws URISyntaxException {
        if (admin.getId() == null) {
            //throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admin.getId())) {
            //throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!adminRepository.existsById(id)) {
            //throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Admin result = adminRepository.save(admin);
        return ResponseEntity
            .ok()
            .body(result);
    }

    /**
     * {@code PATCH  /admins/:id} : Partial updates given fields of an existing admin, field will ignore if it is null
     *
     * @param id the id of the admin to save.
     * @param admin the admin to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admin,
     * or with status {@code 400 (Bad Request)} if the admin is not valid,
     * or with status {@code 404 (Not Found)} if the admin is not found,
     * or with status {@code 500 (Internal Server Error)} if the admin couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
 

    /**
     * {@code GET  /admins} : get all the admins.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of admins in body.
     */
    @GetMapping("/admins")
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    /**
     * {@code GET  /admins/:id} : get the "id" admin.
     *
     * @param id the id of the admin to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admin, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/admins/{id}")
    public Admin getAdmin(@PathVariable Long id) {
        Optional<Admin> admin = adminRepository.findById(id);
        return admin.get();
    }

    /**
     * {@code DELETE  /admins/:id} : delete the "id" admin.
     *
     * @param id the id of the admin to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/admins/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        adminRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .build();
    }
}
