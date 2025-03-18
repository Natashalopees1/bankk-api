package com.fiap.bank.bean.dto;


import com.fiap.bank.bean.enums.TipoConta;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreateAccountRequest {

    private int agencia;

    private String nomeTitular;

    private String cpf;

    private LocalDate dataAbertura;

    private BigDecimal saldo;

    private boolean contaAtiva;

    private TipoConta tipoConta;


}
