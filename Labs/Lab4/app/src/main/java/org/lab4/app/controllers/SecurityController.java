package org.lab4.app.controllers;

import org.lab4.app.models.OwnerDao;
import org.lab4.app.models.SigninRequest;
import org.lab4.app.models.SignupRequest;
import org.lab4.app.repositories.OwnerRepository;
import org.lab4.app.tokenManager.JwtCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class SecurityController
{
    private OwnerRepository ownerRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtCore jwtCore;

    @Autowired
    public void setUserRepository(OwnerRepository ownerRepository)
    {
        this.ownerRepository = ownerRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder)
    {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager)
    {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtCore(JwtCore jwtCore)
    {
        this.jwtCore = jwtCore;
    }

    @PostMapping("/signup")
    ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest)
    {
        if (ownerRepository.existsOwnerByEmail(signupRequest.getEmail()))
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("email already exists");
        }

        var owner = new OwnerDao();
        owner.setName(signupRequest.getOwnerName());
        owner.setEmail(signupRequest.getEmail());
        owner.setBirthDate(signupRequest.getBirthdate());
        owner.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        owner.setRole(signupRequest.getRole());
        ownerRepository.save(owner);

        return ResponseEntity.ok("Successfully signed up");
    }

    @PostMapping("/signin")
    ResponseEntity<?> signup(@RequestBody SigninRequest signinRequest)
    {
        Authentication authentication = null;

        try
        {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getOwnerName(), signinRequest.getPassword()));
        }
        catch (BadCredentialsException e)
        {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtCore.generateToken(authentication);

        return ResponseEntity.ok(jwt);
    }
}
