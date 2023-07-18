package com.wavebl.addressBook;

import com.wavebl.addressBook.constants.CardState;
import com.wavebl.addressBook.controller.CreateBusinessCardCommand;
import com.wavebl.addressBook.domain.model.BusinessCard;
import com.wavebl.addressBook.domain.service.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressBookApplicationTest {
    private static final long CARD_ID_1 = 1L;
    private static final String NAME_1 = "name";
    private static final String ADDRESS_1 = "address";
    private static final long CARD_ID_2 = 2L;
    private static final String NAME_2 = "name2";
    private static final String ADDRESS_2 = "address2";
    private static final long CARD_ID_3 = 3L;
    private static final String NAME_3 = "name3";
    private static final String ADDRESS_3 = "address3";
    private static final long CARD_ID_4 = 4L;
    private static final String NAME_4 = "name4";
    private static final String ADDRESS_4 = "address4";

    @Autowired
    private CreateBusinessCardService createBusinessCardService;

    @Autowired
    private GetListBusinessCardService getListBusinessCardService;

    @Autowired
    private ChangeStatusBusinessCardService changeStatusBusinessCardService;

    @Autowired
    private DeleteBusinessCardService deleteBusinessCardService;

    @Autowired
    private GetBusinessCardService getBusinessCardService;

    @Test
    void contextLoads() {
    }

    @Nested
    class ServicesTestIT {

        @Test
        void scenarioTest() {
            //init DB
            CreateBusinessCardCommand command = new CreateBusinessCardCommand();
            command.setCardId(CARD_ID_1);
            command.setAddress(ADDRESS_1);
            command.setName(NAME_1);
            createBusinessCardService.create(command, CardState.KNOWN);
            command.setCardId(CARD_ID_2);
            command.setAddress(ADDRESS_2);
            command.setName(NAME_2);
            createBusinessCardService.create(command, CardState.UNKNOWN);
            command.setCardId(CARD_ID_3);
            command.setAddress(ADDRESS_3);
            command.setName(NAME_3);
            createBusinessCardService.create(command, CardState.UNKNOWN);
            command.setCardId(CARD_ID_4);
            command.setAddress(ADDRESS_4);
            command.setName(NAME_4);
            createBusinessCardService.create(command, CardState.UNKNOWN);

            // getALL - 3 BusinessCards
            List<BusinessCard> businessCardList = getListBusinessCardService.getList();
            assertEquals(4, businessCardList.size());
            System.out.println("All businessCards:");
            System.out.println(businessCardList);

            //delete CARD_ID_4
            assertNotNull(getBusinessCardService.getById(CARD_ID_4));
            deleteBusinessCardService.delete(CARD_ID_4);
            businessCardList = getListBusinessCardService.getList();
            assertEquals(3, businessCardList.size());
            assertThrows(RuntimeException.class, () -> getBusinessCardService.getById(CARD_ID_4));

            //get with CardState.KNOWN - 1 BusinessCard
            businessCardList = getListBusinessCardService.getList(CardState.KNOWN);
            assertEquals(1, businessCardList.size());
            System.out.println("BusinessCards with status " + CardState.KNOWN.getValue());
            System.out.println(businessCardList);

            //change statuses
            changeStatusBusinessCardService.changeStatus(CARD_ID_1, CardState.STRONG_APPROVED);
            changeStatusBusinessCardService.changeStatus(CARD_ID_2, CardState.MANUAL_APPROVED);
            changeStatusBusinessCardService.changeStatus(CARD_ID_3, CardState.PENDING_VERIFICATION);

            // getAll - 3 BusinessCards with new states
            businessCardList = getListBusinessCardService.getList();
            assertEquals(3, businessCardList.size());
            System.out.println("All businessCards with new states:");
            System.out.println(businessCardList);
        }
    }
}