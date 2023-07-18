package com.wavebl.addressBook.domain.service;

import com.wavebl.addressBook.constants.CardState;
import com.wavebl.addressBook.domain.model.BusinessCard;
import com.wavebl.addressBook.domain.port.DeleteBusinessCardPort;
import com.wavebl.addressBook.domain.port.FindBusinessCardByIdPort;
import com.wavebl.addressBook.domain.port.SaveBusinessCardPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteBusinessCardService {
    private final DeleteBusinessCardPort deleteBusinessCardPort;
    private final FindBusinessCardByIdPort findBusinessCardByIdPort;

    public void delete(long cardId) throws RuntimeException {
        if (!findBusinessCardByIdPort.find(cardId).isPresent()) {
                 throw new RuntimeException(String.format("BusinessCard with id %d not found ", cardId));
        }
        deleteBusinessCardPort.delete(cardId);
    }
}
