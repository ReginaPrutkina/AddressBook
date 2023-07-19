package com.wavebl.addressBook.domain.service;

import com.wavebl.addressBook.constants.CardState;
import com.wavebl.addressBook.exception.BusinessException;
import com.wavebl.addressBook.domain.model.BusinessCard;
import com.wavebl.addressBook.domain.port.FindBusinessCardByIdPort;
import com.wavebl.addressBook.domain.port.SaveBusinessCardPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChangeStatusBusinessCardServiceTest {
    private static final long CARD_ID = 1L;
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final CardState NEW_CARD_STATE = CardState.STRONG_APPROVED;

    @Mock
    private SaveBusinessCardPort saveBusinessCardPort;

    @Mock
    private FindBusinessCardByIdPort findBusinessCardByIdPort;

    @InjectMocks
    private ChangeStatusBusinessCardService subj;

    private BusinessCard businessCard;

    @BeforeEach
    void setUp() {
        businessCard = BusinessCard.builder()
                .cardId(CARD_ID)
                .address(ADDRESS)
                .name(NAME)
                .status(CardState.KNOWN)
                .build();
    }

    @Test
    void changeStatusSuccessTest() {
        when(findBusinessCardByIdPort.find(anyLong())).thenReturn(Optional.of(businessCard));
        when(saveBusinessCardPort.save(any())).thenReturn(businessCard);

        BusinessCard result = subj.changeStatus(CARD_ID, NEW_CARD_STATE);
        verify(findBusinessCardByIdPort).find(CARD_ID);
        verify(saveBusinessCardPort).save(businessCard);

        assertNotNull(result);
        assertEquals(result.getCardId(), CARD_ID);
        assertEquals(result.getAddress(), ADDRESS);
        assertEquals(result.getName(), NAME);
        assertEquals(result.getStatus(), NEW_CARD_STATE);
    }

    @Test
    void changeStatusFailTest() {
        when(findBusinessCardByIdPort.find(anyLong())).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () -> subj.changeStatus(CARD_ID, NEW_CARD_STATE));
        verify(findBusinessCardByIdPort).find(CARD_ID);
        verifyNoInteractions(saveBusinessCardPort);
    }

}