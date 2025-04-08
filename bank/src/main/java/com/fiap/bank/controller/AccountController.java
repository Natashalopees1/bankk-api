package com.fiap.bank.controller;

import com.fiap.bank.bean.dto.*;
import com.fiap.bank.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<GenericResponse> createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        accountService.createAccount(createAccountRequest);
        return ResponseEntity.status(201).body(
                GenericResponse.builder()
                        .message("Conta criada com sucesso")
                        .status(201)
                        .build()
        );
    }

    @GetMapping("/all-by-filter")
    public ResponseEntity<List<AccountResponse>> allByFilter(@RequestParam Map<String, String> params) {
       return ResponseEntity.status(200).body(accountService.getAllByFilter(params));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<GenericResponse> withdraw(@RequestBody TransactionRequest transactionRequest) {
        accountService.withdraw(transactionRequest);
        return ResponseEntity.ok(
                GenericResponse.builder()
                        .message("Saque realizado com sucesso")
                        .status(200)
                        .build()
        );
    }

    @PostMapping("/deposit")
    public ResponseEntity<GenericResponse> deposit(@RequestBody TransactionRequest transactionRequest) {
        accountService.deposit(transactionRequest);
        return ResponseEntity.ok(
                GenericResponse.builder()
                        .message("Depósito realizado com sucesso")
                        .status(200)
                        .build()
        );
    }

    @PostMapping("/pix")
    public ResponseEntity<GenericResponse> pix(@RequestBody PixRequest pixRequest) {
        accountService.pixTransfer(pixRequest);
        return ResponseEntity.ok(
                GenericResponse.builder()
                        .message("Transferência PIX realizada com sucesso")
                        .status(200)
                        .build()
        );
    }

    @DeleteMapping("/close/{accountId}")
    public ResponseEntity<GenericResponse> closeAccount(@PathVariable Long accountId) {
        accountService.closeAccount(accountId);
        return ResponseEntity.ok(
                GenericResponse.builder()
                        .message("Conta encerrada com sucesso")
                        .status(200)
                        .build()
        );
    }



}
