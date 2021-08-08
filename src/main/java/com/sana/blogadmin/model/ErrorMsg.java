package com.sana.blogadmin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMsg {
    private LocalDateTime timestamp;
    private int status;
    private String reason;
    private String message;
}
