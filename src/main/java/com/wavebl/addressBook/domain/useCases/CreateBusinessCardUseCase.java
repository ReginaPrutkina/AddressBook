package com.wavebl.addressBook.domain.useCases;

import com.wavebl.addressBook.constants.CardState;
import com.wavebl.addressBook.controller.CreateBusinessCardCommand;
import com.wavebl.addressBook.domain.model.BusinessCard;

public interface CreateBusinessCardUseCase {
    BusinessCard create(CreateBusinessCardCommand command, CardState state) throws RuntimeException;
}
