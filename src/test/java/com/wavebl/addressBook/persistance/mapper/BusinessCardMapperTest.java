package com.wavebl.addressBook.persistance.mapper;

import com.wavebl.addressBook.constants.CardState;
import com.wavebl.addressBook.controller.CreateBusinessCardCommand;
import com.wavebl.addressBook.domain.model.BusinessCard;
import com.wavebl.addressBook.persistance.entities.BusinessCardEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class BusinessCardMapperTest {
    private static final long CARD_ID = 1L ;
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String STATUS = "KNOWN";

    private final BusinessCardMapper mapper = Mappers.getMapper(BusinessCardMapper.class);

    @Test
    void fromBusinessCardEntityTest(){
        BusinessCardEntity entity = BusinessCardEntity.builder()
                .status(STATUS)
                .cardId(CARD_ID)
                .address(ADDRESS)
                .name(NAME)
                .build();
        BusinessCard result = mapper.map(entity);

        assertNotNull(result);
        assertEquals(result.getCardId(), CARD_ID);
        assertEquals(result.getAddress(), ADDRESS);
        assertEquals(result.getName(), NAME);
        assertEquals(result.getStatus(), CardState.KNOWN);
    }

    @Test
    void fromCreateBusinessCardCommandTest(){
        CreateBusinessCardCommand command = new CreateBusinessCardCommand();
        command.setCardId(CARD_ID);
        command.setName(NAME);
        command.setAddress(ADDRESS);
        BusinessCard result = mapper.map(command, CardState.KNOWN);

        assertNotNull(result);
        assertEquals(result.getCardId(), CARD_ID);
        assertEquals(result.getAddress(), ADDRESS);
        assertEquals(result.getName(), NAME);
        assertEquals(result.getStatus(), CardState.KNOWN);
    }

    @Test
    void fromBusinessCardToEntityTest(){
        BusinessCard businessCard = BusinessCard.builder()
                .status(CardState.KNOWN)
                .cardId(CARD_ID)
                .address(ADDRESS)
                .name(NAME)
                .build();
        BusinessCardEntity result = mapper.mapToEntity(businessCard);

        assertNotNull(result);
        assertEquals(result.getCardId(), CARD_ID);
        assertEquals(result.getAddress(), ADDRESS);
        assertEquals(result.getName(), NAME);
        assertEquals(result.getStatus(), STATUS);
    }

}