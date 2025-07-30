package com.poc.khlassmvp.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private long id;
    private String paymentIdentifier;

    private String name;
    private String phone;
    private String email;

    private CategoryDto category;

}
