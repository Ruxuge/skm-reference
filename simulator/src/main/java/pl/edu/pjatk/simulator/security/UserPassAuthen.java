package pl.edu.pjatk.simulator.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class UserPassAuthen implements AuthenticationProvider {
    @Autowired
    private UserDetImplicationServ userDetImplicationServ;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var username = authentication.getPrincipal().toString();
        var password = authentication.getCredentials().toString();

        var userDetails = userDetImplicationServ.loadUserByUsername(username);
        var matches = userDetImplicationServ.getPasswordEncoder().matches(password, userDetails.getPassword());

        if (matches) {
            return new UserPassAuthen(
                    userDetails.getUsername(),
                    userDetails.getPassword(),
                    userDetails.getAuthorities()
            );
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UserPassAuthen.class.isAssignableFrom(authentication);
    }
}