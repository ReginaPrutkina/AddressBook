package com.wavebl.addressBook.persistance.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "businessCard")
public class BusinessCardEntity {

    @Id
    private long cardId;

    private String name;

    private String address;

    private String status;
}
