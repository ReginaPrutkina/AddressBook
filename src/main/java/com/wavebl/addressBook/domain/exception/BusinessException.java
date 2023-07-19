package com.wavebl.addressBook.domain.exception;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
    public BusinessException(String message, Throwable e) {
        super(message, e);
    }
}
