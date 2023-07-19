package com.wavebl.addressBook.persistance.adapter;

import com.wavebl.addressBook.exception.BusinessException;
import com.wavebl.addressBook.domain.model.BusinessCard;
import com.wavebl.addressBook.domain.port.SaveBusinessCardPort;
import com.wavebl.addressBook.persistance.mapper.BusinessCardMapper;
import com.wavebl.addressBook.persistance.repository.AddressBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveBusinessCardAdapter implements SaveBusinessCardPort {
    private final AddressBookRepository addressBookRepository;
    private final BusinessCardMapper mapper;

    @Override
    public BusinessCard save(BusinessCard businessCard) {
        try {
            addressBookRepository.save(mapper.mapToEntity(businessCard));
        } catch (Exception e) {
            throw new BusinessException("Save error ", e);
        }
        return businessCard;
    }
}
