package com.fiap.bank.bean.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequest {

    private BigDecimal valor;

    private String destinatario;

    private String remetente;
}
