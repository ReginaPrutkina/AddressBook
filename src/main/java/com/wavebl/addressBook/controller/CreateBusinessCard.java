package com.wavebl.addressBook.controller;

import com.wavebl.addressBook.constants.CardState;
import com.wavebl.addressBook.domain.model.BusinessCard;
import com.wavebl.addressBook.domain.useCases.CreateBusinessCardUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreateBusinessCard {
    private final CreateBusinessCardUseCase createBusinessCardUseCase;

    @PostMapping(path = "/unknown")
    public BusinessCard CreateUnKnownBusinessCard(@RequestBody CreateBusinessCardCommand command) {
        command.validate();
        return createBusinessCardUseCase.create(command, CardState.UNKNOWN);
    }

    @PostMapping(path = "/known")
    public BusinessCard CreateKnownBusinessCard(@RequestBody CreateBusinessCardCommand command) {
        command.validate();
        return createBusinessCardUseCase.create(command, CardState.KNOWN);
    }
}
