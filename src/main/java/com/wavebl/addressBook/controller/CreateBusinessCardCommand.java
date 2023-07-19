package com.wavebl.addressBook.controller;

import com.wavebl.addressBook.domain.exception.ValidationException;
import lombok.Data;

@Data
public class CreateBusinessCardCommand {

    private Long cardId;

    private String name;

    private String address;

    void validate() {
        if (cardId == null || name.isBlank() || address.isBlank()) {
         throw new ValidationException("all the data mast not be null or blank ");
        }
    }
}
