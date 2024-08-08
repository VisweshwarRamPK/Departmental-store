package com.viswa.DepartmentalStoreApplication.config;

import com.viswa.DepartmentalStoreApplication.dto.ReturnStatus;
import com.viswa.DepartmentalStoreApplication.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")

public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;


    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Hidden
    @Operation( security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/registerAdmin")
    public ResponseEntity<ReturnStatus> registerAdmin(@RequestBody AuthenticationRequest userDetails) {
        String message= authenticationService.Register(userDetails,"SA");
        return ResponseEntity.ok(new ReturnStatus("Success",message));
    }

    @Operation( security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> register(@RequestBody AuthenticationRequest request) {
        try {
            String message = authenticationService.Register(request, "C");
            RegistrationResponse response = new RegistrationResponse(message);
            HttpStatus status = message.equals("Registration successful") ? HttpStatus.OK : HttpStatus.CONFLICT;
            return new ResponseEntity<>(response, status);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RegistrationResponse("Error occurred during registration"));
        }
    }

    @Operation( security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request){
        String authResponse=null;
        try {
            authResponse = authenticationService.login(request);
            return ResponseEntity.ok(authResponse);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid Username or Password");
        }

    }
    @Operation( security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/assign-role")
    public ResponseEntity<String> assignRole(@RequestBody AuthenticationRequest request) {
        String result = authenticationService.Register(request,"A");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
