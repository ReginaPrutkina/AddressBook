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

@Service
@RequiredArgsConstructor
public class CreateBusinessCardService implements CreateBusinessCardUseCase {
    private final SaveBusinessCardPort saveBusinessCardPort;
    private final FindBusinessCardByIdPort findBusinessCardByIdPort;
    private final BusinessCardMapper mapper;

    @Override
    public BusinessCard create(CreateBusinessCardCommand command, CardState status) throws RuntimeException {
        if (findBusinessCardByIdPort.find(command.getCardId()).isPresent()) {
            throw new RuntimeException(String.format("BusinessCard with id %d already saved ", command.getCardId()));
        }
        BusinessCard businessCard = mapper.map(command, status);
        saveBusinessCardPort.save(businessCard);
        return businessCard;
    }
}
