    package com.example.demo.security;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.core.userdetails.User;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.stereotype.Service;
    import java.util.Arrays;

    @Service
    public class UserDetailsServiceImpl implements UserDetailsService {

        @Autowired
        private AppUserRepository userRepository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            AppUser user = userRepository.findByUsername(username);
            if (user == null) throw new UsernameNotFoundException("User not found");
            return User.withUsername(user.getUsername())
                    .password(user.getPassword())
                    .authorities(user.getRoles().split(","))
                    .build();
        }
    }
