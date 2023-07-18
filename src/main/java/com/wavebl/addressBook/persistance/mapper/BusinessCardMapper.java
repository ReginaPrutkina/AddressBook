package com.wavebl.addressBook.persistance.mapper;

import com.wavebl.addressBook.constants.CardState;
import com.wavebl.addressBook.controller.CreateBusinessCardCommand;
import com.wavebl.addressBook.domain.model.BusinessCard;
import com.wavebl.addressBook.persistance.entities.BusinessCardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface BusinessCardMapper {

    @Mapping(target="status", source="entity.status", qualifiedByName = "getStatusFromString")
    BusinessCard map(BusinessCardEntity entity);

    BusinessCard map(CreateBusinessCardCommand command, CardState status);

    @Mapping(target="status", source="businessCard.status", qualifiedByName = "getStringStatus")
    BusinessCardEntity mapToEntity(BusinessCard businessCard);

    @Named("getStatusFromString")
    default CardState getStatusFromString(String status) {
        // maybe exception - validate ?
        return CardState.valueOf(status);
    }

    @Named("getStringStatus")
    default String getStringStatus(CardState state) {
        return state.getValue();
    }
}
