package com.poc.khlassmvp.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptDto {
    private Long id;
    private LocalDateTime createdAt;

    private UserAccountDto userAccount;

    private BillDto bill;
}
