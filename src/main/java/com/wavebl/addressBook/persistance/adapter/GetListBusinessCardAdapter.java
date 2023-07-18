package com.wavebl.addressBook.persistance.adapter;

import com.wavebl.addressBook.constants.CardState;
import com.wavebl.addressBook.domain.model.BusinessCard;
import com.wavebl.addressBook.domain.port.GetListBusinessCardPort;
import com.wavebl.addressBook.persistance.entities.BusinessCardEntity;
import com.wavebl.addressBook.persistance.mapper.BusinessCardMapper;
import com.wavebl.addressBook.persistance.repository.AddressBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class GetListBusinessCardAdapter implements GetListBusinessCardPort {
    private final AddressBookRepository addressBookRepository;
    private final BusinessCardMapper mapper;

    @Override
    public List<BusinessCard> getList() {

        Iterable<BusinessCardEntity> businessCardEntities = addressBookRepository.findAll();

        return StreamSupport.stream(businessCardEntities.spliterator(), false)
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<BusinessCard> getList(CardState cardState) {
        return addressBookRepository.findByStatus(cardState.getValue())
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }
}
