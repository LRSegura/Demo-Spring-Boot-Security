package com.lab.security.web;


import com.lab.security.config.AuthenticationProviderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final AuthenticationProviderService authenticationManager;

    public LoginController(AuthenticationProviderService authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signing")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.usernameOrEmail(), loginDto.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

    @GetMapping("/csrf")
    public ResponseEntity<String> getCsrf() {
        return new ResponseEntity<>("get successfully", HttpStatus.OK);
    }

    @PostMapping("/csrf")
    public ResponseEntity<String> postCsrf() {
        return new ResponseEntity<>("post successfully", HttpStatus.OK);
    }
}
