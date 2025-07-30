package com.poc.khlassmvp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String paymentIdentifier;

    private String name;
    private String phone;
    private String email;

    @ManyToOne
    private CategoryEntity category;

    @ManyToOne
    private CompanyEntity company;

}
