package com.wavebl.addressBook.controller;

import com.wavebl.addressBook.constants.CardState;
import com.wavebl.addressBook.domain.model.BusinessCard;
import com.wavebl.addressBook.domain.useCases.GetListBusinessCardUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class GetListBusinessCard {
    private final GetListBusinessCardUseCase getListBusinessCardUseCase;

    @GetMapping(path = "/getAll")
    public List<BusinessCard> getList() {
        return getListBusinessCardUseCase.getList();
    }

    @GetMapping(path = "/getListByState")
    public List<BusinessCard> getListByState(@RequestParam String state) {
        CardState status = Arrays.stream(CardState.values())
                .filter(value -> value.getValue().equals(state))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Invalid state"));
        return getListBusinessCardUseCase.getList(status);
    }
}
