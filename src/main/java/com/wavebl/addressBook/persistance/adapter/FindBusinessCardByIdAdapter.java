package com.wavebl.addressBook.persistance.adapter;

import com.wavebl.addressBook.domain.model.BusinessCard;
import com.wavebl.addressBook.domain.port.FindBusinessCardByIdPort;
import com.wavebl.addressBook.persistance.entities.BusinessCardEntity;
import com.wavebl.addressBook.persistance.mapper.BusinessCardMapper;
import com.wavebl.addressBook.persistance.repository.AddressBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindBusinessCardByIdAdapter implements FindBusinessCardByIdPort {
    private final AddressBookRepository addressBookRepository;
    private final BusinessCardMapper mapper;

    @Override
    public Optional<BusinessCard> find(Long cardId) {
         Optional<BusinessCardEntity> optionalBusinessCardEntity =  addressBookRepository.findById(cardId);
         return optionalBusinessCardEntity.map(mapper::map);
    }
}
