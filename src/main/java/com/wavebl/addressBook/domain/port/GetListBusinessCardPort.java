package com.wavebl.addressBook.domain.port;

import com.wavebl.addressBook.constants.CardState;
import com.wavebl.addressBook.domain.model.BusinessCard;

import java.util.List;

public interface GetListBusinessCardPort {
    List<BusinessCard> getList();

    List<BusinessCard> getList(CardState cardState);
}
