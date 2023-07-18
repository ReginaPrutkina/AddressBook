package com.wavebl.addressBook.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CardState {
    UNKNOWN("UNKNOWN"),
    KNOWN("KNOWN"),
    PENDING_VERIFICATION("PENDING_VERIFICATION"),
    STRONG_APPROVED("STRONG_APPROVED"),
    ADDITIONAL_VERIFICATION("ADDITIONAL_VERIFICATION"),
    MANUAL_APPROVED("MANUAL_APPROVED");

    private final String value;
}
