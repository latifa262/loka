package emsi.iir4.loka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import emsi.iir4.loka.config.CustomAuthenticationProvider;
import emsi.iir4.loka.config.SecurityUtils;
import emsi.iir4.loka.domain.User;
import emsi.iir4.loka.repository.UserRepository;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User login(String userName, String password) {
        User user = userRepository.findUserByUserName(userName).get();
        if (user == null) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        Authentication auth = new CustomAuthenticationProvider()
                .authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        return user;
    }

    public User currentUser() {
        return userRepository.findUserByUserName(SecurityUtils.getCurrentUserLogin().get()).get();
    }

}
