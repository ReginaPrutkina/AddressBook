package com.wavebl.addressBook.domain.useCases;

import com.wavebl.addressBook.constants.CardState;
import com.wavebl.addressBook.controller.CreateBusinessCardCommand;
import com.wavebl.addressBook.domain.model.BusinessCard;

import java.util.List;

public interface GetListBusinessCardUseCase {
    List<BusinessCard> getList() throws RuntimeException;

    List<BusinessCard> getList(CardState state) throws RuntimeException;
}
