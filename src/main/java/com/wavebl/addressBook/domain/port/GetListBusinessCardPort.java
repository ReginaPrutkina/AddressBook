package com.wavebl.addressBook.domain.port;

import com.wavebl.addressBook.constants.CardState;
import com.wavebl.addressBook.domain.model.BusinessCard;

import java.util.List;
import java.util.Optional;

public interface GetListBusinessCardPort {
    List<BusinessCard> getList();

    List<BusinessCard> getList(CardState cardState);
}
