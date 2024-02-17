package it.epicode.w6d4p.controller;

import it.epicode.w6d4p.DTO.UserDTO;
import it.epicode.w6d4p.DTO.UserLoginDTO;
import it.epicode.w6d4p.exception.BadRequestException;
import it.epicode.w6d4p.exception.LoginException;
import it.epicode.w6d4p.model.User;
import it.epicode.w6d4p.security.JWTTools;
import it.epicode.w6d4p.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTTools jwtTools;
    @PostMapping("/auth/register")
    public ResponseEntity<User> register(@RequestBody @Validated UserDTO userDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        return new ResponseEntity<User>(authService.createUser(userDTO), HttpStatus.OK);

    }

    @PostMapping("/auth/login")
    public String login(@RequestBody @Validated UserLoginDTO userLoginDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        User user = authService.getUserByEmail(userLoginDTO.getEmail());

        if (passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())){
            return jwtTools.generateToken(user);
        }
        else{
            throw new LoginException("Credenziali errate");
        }
    }

}
