package pl.edu.pjatk.simulator.security;

import org.springframework.stereotype.Service;

@Service
public class UserDetImplicationServ {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserDetImplicationServ(UserRepository repository) {
        this.userRepository = repository;
        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found."));

        return new UserDetImplicationuser);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }
}