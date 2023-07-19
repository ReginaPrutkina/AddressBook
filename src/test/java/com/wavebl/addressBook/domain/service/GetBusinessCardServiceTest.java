package com.wavebl.addressBook.domain.service;

import com.wavebl.addressBook.domain.exception.BusinessException;
import com.wavebl.addressBook.domain.model.BusinessCard;
import com.wavebl.addressBook.domain.port.FindBusinessCardByIdPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetBusinessCardServiceTest {
    private static final long CARD_ID = 1L;

    @Mock
    private FindBusinessCardByIdPort findBusinessCardByIdPort;

    @InjectMocks
    private GetBusinessCardService subj;

    @Test
    void getBusinessCardSuccessTest() {
        BusinessCard businessCard = BusinessCard.builder().cardId(CARD_ID).build();
        when(findBusinessCardByIdPort.find(anyLong())).thenReturn(Optional.of(businessCard));

        BusinessCard result = subj.getById(CARD_ID);

        verify(findBusinessCardByIdPort).find(CARD_ID);

        assertEquals(CARD_ID, result.getCardId());
    }

    @Test
    void getBusinessCardFailTest() {
        when(findBusinessCardByIdPort.find(anyLong())).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () -> subj.getById(CARD_ID));

        verify(findBusinessCardByIdPort).find(CARD_ID);
    }
}