package emsi.iir4.loka.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import emsi.iir4.loka.service.UserService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration {

    public SecurityConfiguration(

    ) {
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Autowired
    private UserService authenticationProvider;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .csrf()
            .disable()
            .exceptionHandling()
        .and()
            .headers()
        .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
        .and()
            .authorizeRequests()
            .antMatchers("/api/authenticate").permitAll()
            .antMatchers("/api/register").permitAll()
            .antMatchers("/api/client").hasAuthority(AuthoritiesConstants.CLIENT)
            .antMatchers("/api/dev").hasAuthority(AuthoritiesConstants.DEV)
            .antMatchers("/api/admin").hasAuthority(AuthoritiesConstants.ADMIN)
            .antMatchers("/api/users/**").hasAuthority(AuthoritiesConstants.ADMIN)
            .antMatchers("/tickets/add").hasAuthority(AuthoritiesConstants.CLIENT)
            .antMatchers("/attribuer").hasAuthority(AuthoritiesConstants.ADMIN)
            .antMatchers("/api/**").authenticated()

        .and()
        .formLogin(form -> form
        .loginPage("/login").permitAll())
            ;
        return http.build();
        // @formatter:on
    }
}
