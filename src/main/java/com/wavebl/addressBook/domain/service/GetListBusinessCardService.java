package com.wavebl.addressBook.domain.service;

import com.wavebl.addressBook.constants.CardState;
import com.wavebl.addressBook.domain.model.BusinessCard;
import com.wavebl.addressBook.domain.port.GetListBusinessCardPort;
import com.wavebl.addressBook.domain.useCases.GetListBusinessCardUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetListBusinessCardService implements GetListBusinessCardUseCase {
    private final GetListBusinessCardPort getListBusinessCardPort;

    public List<BusinessCard> getList() throws RuntimeException {
        return getListBusinessCardPort.getList();
    }

    public List<BusinessCard> getList(CardState cardState) throws RuntimeException {
        return getListBusinessCardPort.getList(cardState);
    }
}
