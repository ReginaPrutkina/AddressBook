package com.wavebl.addressBook.domain.port;

import com.wavebl.addressBook.domain.model.BusinessCard;

import java.util.Optional;

public interface FindBusinessCardByIdPort {
    Optional<BusinessCard> find(Long cardId);
}
