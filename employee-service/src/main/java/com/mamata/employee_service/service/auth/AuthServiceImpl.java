package com.mamata.employee_service.service.auth;

import com.mamata.employee_service.dto.auth.LoginRequest;
import com.mamata.employee_service.dto.auth.LoginResponse;
import com.mamata.employee_service.dto.auth.ResisterRequest;
import com.mamata.employee_service.entity.Role;
import com.mamata.employee_service.entity.RoleName;
import com.mamata.employee_service.entity.User;
import com.mamata.employee_service.repository.RoleRepository;
import com.mamata.employee_service.repository.UserRepository;
import com.mamata.employee_service.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;


    @Override
    public void register(ResisterRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("User Already exist");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email Already exist");
        }

        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(
                encoder.encode(request.getPassword())
        );
        user.setEnabled(true);

        Role role = roleRepository.findByName(RoleName.ROLE_USER).
                orElseThrow(() -> new RuntimeException("Role not found"));

        user.setRoles(Set.of(role));
        userRepository.save(user);

    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        String token = jwtService.generateToken(userDetails);
        return new LoginResponse(token);
    }
}
