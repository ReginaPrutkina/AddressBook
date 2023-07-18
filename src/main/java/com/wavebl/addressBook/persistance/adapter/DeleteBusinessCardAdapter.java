package com.wavebl.addressBook.persistance.adapter;

import com.wavebl.addressBook.domain.port.DeleteBusinessCardPort;
import com.wavebl.addressBook.persistance.repository.AddressBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteBusinessCardAdapter implements DeleteBusinessCardPort {
    private final AddressBookRepository addressBookRepository;

    @Override
    public void delete(Long cardId) {
        addressBookRepository.deleteById(cardId);
    }
}
