package com.fiap.bank.bean.dto;

import com.fiap.bank.bean.enums.TipoConta;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AccountResponse {

    private int agencia;

    private String nomeTitular;

    private String cpf;

    private LocalDate dataAbertura;

    private double saudoInicial;

    private boolean contaAtiva;

    private TipoConta tipoConta;
}
