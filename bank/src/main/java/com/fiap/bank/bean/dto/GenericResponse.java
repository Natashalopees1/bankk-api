package com.fiap.bank.bean.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GenericResponse {

    private int status;

    private String message;
}
