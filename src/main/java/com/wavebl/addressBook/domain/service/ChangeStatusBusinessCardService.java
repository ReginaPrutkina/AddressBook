package com.wavebl.addressBook.domain.service;

import com.wavebl.addressBook.constants.CardState;
import com.wavebl.addressBook.controller.CreateBusinessCardCommand;
import com.wavebl.addressBook.domain.model.BusinessCard;
import com.wavebl.addressBook.domain.port.FindBusinessCardByIdPort;
import com.wavebl.addressBook.domain.port.SaveBusinessCardPort;
import com.wavebl.addressBook.domain.useCases.CreateBusinessCardUseCase;
import com.wavebl.addressBook.persistance.mapper.BusinessCardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChangeStatusBusinessCardService {
    private final SaveBusinessCardPort saveBusinessCardPort;
    private final FindBusinessCardByIdPort findBusinessCardByIdPort;

    public BusinessCard changeStatus(long cardId, CardState newStatus) throws RuntimeException {
        BusinessCard businessCard = findBusinessCardByIdPort.find(cardId)
                .orElseThrow(() -> new RuntimeException(String.format("BusinessCard with id %d not found ", cardId)));
        businessCard.setStatus(newStatus);
        saveBusinessCardPort.save(businessCard);
        return businessCard;
    }
}
