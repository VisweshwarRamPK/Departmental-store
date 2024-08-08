package com.viswa.DepartmentalStoreApplication.service;
import com.viswa.DepartmentalStoreApplication.config.AuthenticationRequest;
import com.viswa.DepartmentalStoreApplication.config.JwtService;
import com.viswa.DepartmentalStoreApplication.model.Role;
import com.viswa.DepartmentalStoreApplication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service

public class AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
    private final PasswordEncoder passwordEncoder;


    private boolean adminRegistered ;
    public AuthenticationService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;


    }
    public String assignRoles(String userName, String password) {
        Optional<User> usr = userService.findByUserName(userName);
        if(usr.get()!=null){
            return new RuntimeException("Admin name already exists").getMessage();
        }
        else{
            User user = new User();
            user.setUsername(userName);
            user.setPassword(passwordEncoder.encode(password));
            user.setRole(Role.ADMIN);
            userService.addUser(user);
            return "Role assigned successfully";
        }


    }
    public String Register(AuthenticationRequest user, String role) {
        try {
            // Validate input
            if (user == null || user.getUsername() == null || user.getPassword() == null) {
                return "Invalid input";
            }

            // Check if user already exists
            if (userService.findByUserName(user.getUsername()).get() != null) {
                return "Username already exists";
            }
            Role r = switch (role) {
                case "C" -> Role.CUSTOMER;
                case "A" -> Role.ADMIN;
                case "SA"->Role.SUPERADMIN;
                default -> null;
            };
            User newUser = new User(user.getUsername(), passwordEncoder.encode(user.getPassword()),r);
            userService.addUser(newUser);

            return "Registration successful";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred during registration";
        }
    }

    public String login(AuthenticationRequest authenticationRequest) {
        try{
            UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),authenticationRequest.getPassword()
            );
            authenticationManager.authenticate(authToken);
            Optional<User> user = userService.findByUserName(authenticationRequest.getUsername());
            User usr = user.get();
            if(usr==null){
                throw new UsernameNotFoundException("Username not found");
            }
            String jwt=jwtService.generateToken(usr,generateExtraClaims(usr));
            return jwt;
        }
        catch(AuthenticationException e){
            e.printStackTrace();
            throw new BadCredentialsException("Invalid User name or password");
        }
    }

    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("username", user.getUsername());
        extraClaims.put("role", user.getRole().name());
        return extraClaims;
    }

}