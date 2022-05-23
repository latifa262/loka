package emsi.iir4.loka.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import emsi.iir4.loka.config.SecurityUtils;
import emsi.iir4.loka.domain.User;
import emsi.iir4.loka.repository.UserRepository;
@Service
public class UserService  implements AuthenticationProvider {

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
        Authentication auth = authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        return user;
    }

    public User currentUser() {
        return userRepository.findUserByUserName(SecurityUtils.getCurrentUserLogin().get()).get();
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userRepository.findUserByUserName(username).get();

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getAuthority().toString())); 
        System.out.println(authorities.get(0));
        // store user in security context*
        SecurityContextHolder.  getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, password, authorities));


        return new UsernamePasswordAuthenticationToken(username, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
