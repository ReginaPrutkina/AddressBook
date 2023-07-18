package com.wavebl.addressBook.domain.service;

import com.wavebl.addressBook.constants.CardState;
import com.wavebl.addressBook.controller.CreateBusinessCardCommand;
import com.wavebl.addressBook.domain.model.BusinessCard;
import com.wavebl.addressBook.domain.port.FindBusinessCardByIdPort;
import com.wavebl.addressBook.domain.port.SaveBusinessCardPort;
import com.wavebl.addressBook.domain.useCases.CreateBusinessCardUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetBusinessCardService {
    private final FindBusinessCardByIdPort findBusinessCardByIdPort;

    public BusinessCard getById(long cardId) throws RuntimeException {
        return findBusinessCardByIdPort.find(cardId)
                .orElseThrow(() -> new RuntimeException(String.format("BusinessCard with %d not found ", cardId)));
    }
}
