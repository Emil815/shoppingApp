package az.example.online.shopping.infrastructure.web.controller;

import az.example.online.shopping.domain.handler.comman.concretes.SignUpCommandHandler;
import az.example.online.shopping.infrastructure.web.dto.request.command.LoginCommand;
import az.example.online.shopping.infrastructure.web.dto.request.command.RefreshTokenCommand;
import az.example.online.shopping.infrastructure.web.dto.request.command.UserSignUpCommand;
import az.example.online.shopping.infrastructure.web.dto.response.AuthResponseModel;
import az.example.online.shopping.infrastructure.web.dto.response.ResponseModel;
import az.example.online.shopping.infrastructure.web.service.abstracts.AbstractJwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final SignUpCommandHandler signUpCommandHandler;
    private final AuthenticationManager authenticationManager;
    private final AbstractJwtService jwtService;


    @PostMapping("/signup")
    public ResponseModel<String> signup(@RequestBody UserSignUpCommand command) {
        signUpCommandHandler.handle(command);
        return ResponseModel.created("User Successfully Created");
    }

    @PostMapping("/login")
    public ResponseModel<AuthResponseModel> login(@RequestBody LoginCommand request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getPhoneNumber(), request.getPassword()));

        String accessToken = jwtService.generateAccessToken(request.getPhoneNumber());
        String refreshToken = jwtService.generateRefreshToken(request.getPhoneNumber());

        return ResponseModel.accepted(
                AuthResponseModel.builder().accessToken(accessToken).refreshToken(refreshToken).build(),
                "User Successfully Login");
    }

    @PostMapping("/refresh")
    public ResponseModel<AuthResponseModel> refresh(@RequestBody RefreshTokenCommand command) {
        String accessToken =
                jwtService.validateRefreshTokenAndGenerateAccessToken(
                        command.getRefreshToken());
        return ResponseModel.ok(
                AuthResponseModel.builder().accessToken(accessToken).build(),
                "User Successfully Refreshed");
    }
}
