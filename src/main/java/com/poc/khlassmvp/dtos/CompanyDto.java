package com.poc.khlassmvp.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;

    private CompanyAccountDto companyAccount;
}
