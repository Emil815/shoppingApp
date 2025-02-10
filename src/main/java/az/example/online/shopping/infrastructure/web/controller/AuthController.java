package az.example.online.shopping.infrastructure.web.controller;

import az.example.online.shopping.domain.handler.command.concretes.SignUpCommandHandler;
import az.example.online.shopping.domain.handler.query.UserInfoQueryHandler;
import az.example.online.shopping.infrastructure.web.dto.request.command.LoginCommand;
import az.example.online.shopping.infrastructure.web.dto.request.command.UserSignUpCommand;
import az.example.online.shopping.infrastructure.web.dto.response.AuthResponseModel;
import az.example.online.shopping.infrastructure.web.dto.response.SignUpResponseModel;
import az.example.online.shopping.infrastructure.web.dto.response.UserInfoResponseModel;
import az.example.online.shopping.infrastructure.web.service.abstracts.AbstractJwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {
    private final SignUpCommandHandler signUpCommandHandler;
    private final UserInfoQueryHandler userInfoQueryHandler;
    private final AuthenticationManager authenticationManager;
    private final AbstractJwtService jwtService;


    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseModel> signup(@RequestBody UserSignUpCommand command) {
        signUpCommandHandler.handle(command);
        return new ResponseEntity<>(new SignUpResponseModel("User successfully signed"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseModel> login(@RequestBody LoginCommand request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getPhoneNumber(), request.getPassword()));

        String accessToken = jwtService.generateAccessToken(request.getPhoneNumber());
        String refreshToken = jwtService.generateRefreshToken(request.getPhoneNumber());

        return new ResponseEntity<>(AuthResponseModel.builder().accessToken(accessToken).refreshToken(refreshToken).build(), HttpStatus.ACCEPTED);

    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseModel> refresh(HttpServletRequest request,
                                                     @NonNull HttpServletResponse response) {
        AuthResponseModel responseModel =
                jwtService.validateRefreshTokenAndGenerateAccessToken(request, response);
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @PostMapping("/me")
    public ResponseEntity<UserInfoResponseModel> me(HttpServletRequest request
    ) {
        return new ResponseEntity<>(userInfoQueryHandler.handle(request), HttpStatus.OK);
    }
}
