package com.wavebl.addressBook.domain.port;

import com.wavebl.addressBook.domain.model.BusinessCard;

public interface SaveBusinessCardPort {
    BusinessCard save(BusinessCard businessCard);
}
