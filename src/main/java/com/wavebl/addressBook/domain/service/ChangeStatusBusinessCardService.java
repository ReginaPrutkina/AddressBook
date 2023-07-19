package com.wavebl.addressBook.domain.service;

import com.wavebl.addressBook.constants.CardState;
import com.wavebl.addressBook.exception.BusinessException;
import com.wavebl.addressBook.domain.model.BusinessCard;
import com.wavebl.addressBook.domain.port.FindBusinessCardByIdPort;
import com.wavebl.addressBook.domain.port.SaveBusinessCardPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangeStatusBusinessCardService {
    private final SaveBusinessCardPort saveBusinessCardPort;
    private final FindBusinessCardByIdPort findBusinessCardByIdPort;

    public BusinessCard changeStatus(long cardId, CardState newStatus) throws RuntimeException {
        BusinessCard businessCard = findBusinessCardByIdPort.find(cardId)
                .orElseThrow(() -> new BusinessException(String.format("BusinessCard with id %d not found ", cardId)));
        businessCard.setStatus(newStatus);
        saveBusinessCardPort.save(businessCard);
        return businessCard;
    }
}
