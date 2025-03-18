package com.fiap.bank.bean.model;

import com.fiap.bank.bean.enums.TipoConta;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "accounts") // Nome da tabela no banco de dados
public class AccountModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int agencia;

    private String nomeTitular;

    private String cpf;

    private LocalDate dataAbertura;

    private BigDecimal saldo;

    private boolean contaAtiva;

    private TipoConta tipoConta;
}
