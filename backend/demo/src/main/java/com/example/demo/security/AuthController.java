    package com.example.demo.security;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.core.AuthenticationException;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/api/auth")
    public class AuthController {

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private JwtUtil jwtUtil;

        @Autowired
        private AppUserRepository userRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @PostMapping("/login")
        public String login(@RequestBody AuthRequest authRequest) {
            try {
                Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
                );
                return jwtUtil.generateToken(authRequest.getUsername());
            } catch (AuthenticationException e) {
                throw new RuntimeException("Invalid credentials");
            }
        }

        @PostMapping("/signup")
        public String signup(@RequestBody AuthRequest authRequest) {
            if (userRepository.existsByUsername(authRequest.getUsername())) {
                throw new RuntimeException("Username already exists");
            }
            AppUser user = new AppUser();
            user.setUsername(authRequest.getUsername());
            user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
            userRepository.save(user);
            return "User registered successfully";
        }
    }
