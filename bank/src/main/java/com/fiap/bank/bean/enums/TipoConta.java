package com.fiap.bank.bean.enums;

public enum TipoConta {
    CORRENTE("CORRENTE"),
    POUPANCA("POUPANCA"),
    SALARIO("SALARIO");

    private final String valor;

    TipoConta(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return valor;
    }
}
