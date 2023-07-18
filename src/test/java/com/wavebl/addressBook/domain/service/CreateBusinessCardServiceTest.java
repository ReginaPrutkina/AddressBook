package com.wavebl.addressBook.domain.service;

import com.wavebl.addressBook.constants.CardState;
import com.wavebl.addressBook.controller.CreateBusinessCardCommand;
import com.wavebl.addressBook.domain.model.BusinessCard;
import com.wavebl.addressBook.domain.port.FindBusinessCardByIdPort;
import com.wavebl.addressBook.domain.port.SaveBusinessCardPort;
import com.wavebl.addressBook.persistance.mapper.BusinessCardMapper;
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
class CreateBusinessCardServiceTest {
    private static final long CARD_ID = 1L;
    private static final String NAME = "name";
    private static final String ADDRESS = "address";

    @Mock
    private SaveBusinessCardPort saveBusinessCardPort;

    @Mock
    private FindBusinessCardByIdPort findBusinessCardByIdPort;

    @Mock
    private BusinessCardMapper mapper;

    @InjectMocks
    private CreateBusinessCardService subj;

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
    void createSuccessTest() {
        when(findBusinessCardByIdPort.find(anyLong())).thenReturn(Optional.empty());
        when(mapper.map(any(), any())).thenReturn(businessCard);
        when(saveBusinessCardPort.save(any())).thenReturn(businessCard);
        CreateBusinessCardCommand command = new CreateBusinessCardCommand();
        command.setCardId(CARD_ID);
        BusinessCard result = subj.create(command, CardState.KNOWN);
        verify(findBusinessCardByIdPort).find(CARD_ID);
        verify(mapper).map(command, CardState.KNOWN);
        verify(saveBusinessCardPort).save(businessCard);

        assertNotNull(result);
        assertEquals(result.getCardId(), CARD_ID);
        assertEquals(result.getAddress(), ADDRESS);
        assertEquals(result.getName(), NAME);
        assertEquals(result.getStatus(), CardState.KNOWN);
    }

    @Test
    void createFailTest() {
        when(findBusinessCardByIdPort.find(anyLong())).thenReturn(Optional.of(BusinessCard.builder().build()));

        CreateBusinessCardCommand command = new CreateBusinessCardCommand();
        command.setCardId(CARD_ID);
        assertThrows(RuntimeException.class, () -> subj.create(command, CardState.KNOWN));
        verify(findBusinessCardByIdPort).find(CARD_ID);
        verifyNoInteractions(mapper);
        verifyNoInteractions(saveBusinessCardPort);
    }
}