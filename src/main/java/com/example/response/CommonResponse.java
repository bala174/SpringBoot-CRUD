package com.example.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@NoArgsConstructor

public class CommonResponse {

    private String status;

    private String errorMessage;

    private String successMessage;

    private Object data;

    private int code;
}
