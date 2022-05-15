package emsi.iir4.loka.web.controller;

import emsi.iir4.loka.domain.User;
import emsi.iir4.loka.repository.UserRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * REST controller for managing {@link emsi.iir4.loka.domain.User}.
 */
@Controller
@RequestMapping("/api/users")
@Transactional
public class UserWeb {

    private static final String ENTITY_NAME = "User";

    @Value("ticket")
    private String applicationName;
    @Autowired
    private UserRepository userRepository;

    @PutMapping("/{id}")
    public String update(@PathVariable final Long id, @RequestBody User user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException("invalide id");
        }
        if (!Objects.equals(id, user.getId())) {
            throw new IllegalArgumentException("bad id");
        }

        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("duplicate id");
        }

        User result = userRepository.save(user);
        return "redirect:/index";
    }

    @GetMapping
    public ModelAndView getAllUsers() {
        List<User> users = userRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("redirect:/index");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getUSer(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/index");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public String deleteAdmin(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/index";
    }
}
