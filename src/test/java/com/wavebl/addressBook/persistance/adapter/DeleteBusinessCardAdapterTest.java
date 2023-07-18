package com.wavebl.addressBook.persistance.adapter;

import com.wavebl.addressBook.persistance.repository.AddressBookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteBusinessCardAdapterTest {
    private static final long CARD_ID = 1L;

    @Mock
    private AddressBookRepository addressBookRepository;

    @InjectMocks
    private DeleteBusinessCardAdapter subj;

    @Test
    void deleteTest() {
        subj.delete(CARD_ID);
        verify(addressBookRepository).deleteById(CARD_ID);
    }
}