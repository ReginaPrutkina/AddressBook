package com.wavebl.addressBook.controller;

import lombok.Data;

@Data
public class CreateBusinessCardCommand {
    private long cardId;
    private String name;
    private String address;
}
