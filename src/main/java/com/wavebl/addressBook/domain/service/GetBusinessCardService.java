package com.wavebl.addressBook.domain.service;

import com.wavebl.addressBook.exception.BusinessException;
import com.wavebl.addressBook.domain.model.BusinessCard;
import com.wavebl.addressBook.domain.port.FindBusinessCardByIdPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetBusinessCardService {
    private final FindBusinessCardByIdPort findBusinessCardByIdPort;

    public BusinessCard getById(long cardId) throws RuntimeException {
        return findBusinessCardByIdPort.find(cardId)
                .orElseThrow(() -> new BusinessException(String.format("BusinessCard with %d not found ", cardId)));
    }
}
