package com.wavebl.addressBook.domain.model;

import com.wavebl.addressBook.constants.CardState;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BusinessCard {
    private long cardId;
    private String name;
    private String address;
    private CardState status;
}
