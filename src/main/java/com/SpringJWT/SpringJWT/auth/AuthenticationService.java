package com.SpringJWT.SpringJWT.auth;

import com.SpringJWT.SpringJWT.JwtService;
import com.SpringJWT.SpringJWT.Role;
import com.SpringJWT.SpringJWT.User;
import com.SpringJWT.SpringJWT.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

   private final UserRepo repo;
   private final PasswordEncoder passwordEncoder;
   private final JwtService jwtService;
   private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request){

        var user = User.builder()
                .fname(request.getFirstname())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();

        repo.save(user);

        var jwtToken = jwtService.generateToken(user);
       return AuthenticationResponse.builder()
               .token(jwtToken)
               .build();
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request){
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );

        Optional<User> users= repo.findByEmail(request.getEmail());
        if(users.isEmpty()){
            throw new UsernameNotFoundException("user not found:"+ request.getEmail());
        }
        System.out.print("we going to find user___________");
        var user = repo.findByEmail(request.getEmail())
                .orElseThrow();
        System.out.print("we find user");
        System.out.print("we find user"+user);;
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
