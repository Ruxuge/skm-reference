package pl.edu.pjatk.simulator.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.GET, "/trains/**", "/compartments/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MODERATOR", "ROLE_USER")
                .antMatchers("/trains/**", "/compartments/**", "/move").hasAnyAuthority("ROLE_ADMIN", "ROLE_MODERATOR")
                .antMatchers("/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
                .addFilter(new AuthenFilter(authenticationManager()))
                .addFilter(new AuthorFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable();
    }
}