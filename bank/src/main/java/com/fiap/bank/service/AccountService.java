package com.fiap.bank.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.bank.bean.dto.AccountResponse;
import com.fiap.bank.bean.dto.CreateAccountRequest;
import com.fiap.bank.bean.dto.PixRequest;
import com.fiap.bank.bean.dto.TransactionRequest;
import com.fiap.bank.bean.model.AccountModel;
import com.fiap.bank.repository.AccountEspecification;
import com.fiap.bank.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    private final ObjectMapper objectMapper;

    public AccountService(AccountRepository accountRepository, ObjectMapper objectMapper) {
        this.accountRepository = accountRepository;
        this.objectMapper = objectMapper;
    }

    public void createAccount(CreateAccountRequest createAccountRequest) {
        AccountModel accountModel = objectMapper.convertValue(createAccountRequest, AccountModel.class);
        accountRepository.save(accountModel);
    }


    public List<AccountResponse> getAllByFilter(Map<String, String> params) {
        List<AccountModel> accounts = accountRepository.findAll(AccountEspecification.filterByParams(params));

        return accounts.stream()
                .map(account -> objectMapper.convertValue(account, AccountResponse.class))
                .collect(Collectors.toList());
    }

    public void closeAccount(Long accountId) {
        accountRepository.deleteById(accountId);
    }

    @Transactional
    public void pixTransfer(PixRequest pixRequest) {
        AccountModel remetente = accountRepository.findByCpf(pixRequest.getRemetente())
                .orElseThrow(() -> new RuntimeException("Conta do remetente não encontrada"));

        AccountModel destinatario = accountRepository.findByCpf(pixRequest.getDestinatario())
                .orElseThrow(() -> new RuntimeException("Conta do destinatário não encontrada"));

        if (remetente.getSaldo().compareTo(pixRequest.getValor()) < 0) {
            throw new RuntimeException("Saldo insuficiente para a transferência");
        }

        remetente.setSaldo(remetente.getSaldo().subtract(pixRequest.getValor()));
        destinatario.setSaldo(destinatario.getSaldo().add(pixRequest.getValor()));


        accountRepository.save(remetente);
        accountRepository.save(destinatario);
    }

    public void deposit(TransactionRequest transactionRequest) {
        AccountModel accountModel = accountRepository.findByCpf(transactionRequest.getRemetente())
                .orElseThrow(() -> new RuntimeException("Conta do remetente não encontrada"));

        accountModel.setSaldo(accountModel.getSaldo().add(transactionRequest.getValor()));
        accountRepository.save(accountModel);

    }

    public void withdraw(TransactionRequest transactionRequest) {
        AccountModel accountModel = accountRepository.findByCpf(transactionRequest.getRemetente())
                .orElseThrow(() -> new RuntimeException("Conta do remetente não encontrada"));

        if (accountModel.getSaldo().compareTo(transactionRequest.getValor()) < 0) {
            throw new RuntimeException("Saldo insuficiente para a sacar");
        }

        accountModel.setSaldo(accountModel.getSaldo().subtract(transactionRequest.getValor()));
        accountRepository.save(accountModel);
    }
}
