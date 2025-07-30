package com.poc.khlassmvp.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDto {
    private Long id;
    private double amount;
    private LocalDate expiryDate;

    private ClientDto client;

    private CompanyDto company;
}
