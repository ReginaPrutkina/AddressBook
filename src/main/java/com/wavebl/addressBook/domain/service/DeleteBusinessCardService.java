package com.wavebl.addressBook.domain.service;

import com.wavebl.addressBook.domain.exception.BusinessException;
import com.wavebl.addressBook.domain.port.DeleteBusinessCardPort;
import com.wavebl.addressBook.domain.port.FindBusinessCardByIdPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteBusinessCardService {
    private final DeleteBusinessCardPort deleteBusinessCardPort;
    private final FindBusinessCardByIdPort findBusinessCardByIdPort;

    public void delete(long cardId) throws RuntimeException {
        if (findBusinessCardByIdPort.find(cardId).isEmpty()) {
                 throw new BusinessException(String.format("BusinessCard with id %d not found ", cardId));
        }
        deleteBusinessCardPort.delete(cardId);
    }
}
