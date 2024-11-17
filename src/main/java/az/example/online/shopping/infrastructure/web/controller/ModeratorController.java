package az.example.online.shopping.infrastructure.web.controller;


import az.example.online.shopping.domain.handler.comman.concretes.*;
import az.example.online.shopping.infrastructure.web.dto.request.command.ChangeBasketStatusCommand;
import az.example.online.shopping.infrastructure.web.dto.response.BasketForModeratorResponseModel;
import az.example.online.shopping.infrastructure.web.dto.response.BasketResponseModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moderator")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ModeratorController {
    private final ConfirmBasketCommandHandler confirmBasketCommandHandler;
    private final RejectBasketCommandHandler rejectBasketCommandHandler;
    private final PaidBasketCommandHandler paidBasketCommandHandler;
    private final BasketForModeratorQueryHandler basketForModeratorQueryHandler;


    @PostMapping("/confirm")
    public ResponseEntity<String> approve(ChangeBasketStatusCommand command) {
        confirmBasketCommandHandler.handle(command);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/reject")
    public ResponseEntity<String> reject(ChangeBasketStatusCommand command) {
        rejectBasketCommandHandler.handle(command);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/paid")
    public ResponseEntity<String> paid(ChangeBasketStatusCommand command) {
        paidBasketCommandHandler.handle(command);
        return ResponseEntity.ok("Success");
    }


    @GetMapping("/query")
    public ResponseEntity<List<BasketForModeratorResponseModel>> query() {

        return ResponseEntity.ok(basketForModeratorQueryHandler.handle());
    }



}
