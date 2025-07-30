package com.poc.khlassmvp.config;

import com.poc.khlassmvp.entities.BillEntity;
import com.poc.khlassmvp.entities.CategoryEntity;
import com.poc.khlassmvp.entities.ClientEntity;
import com.poc.khlassmvp.entities.CompanyEntity;
import com.poc.khlassmvp.services.*;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DbInitializer implements CommandLineRunner {
    private final CompanyService companyService;
    private final ClientService clientService;
    private final CategoryService categoryService;
    private final BillService billService;
//    private final ReceiptService receiptService;
//    private final CompanyAccountService companyAccountService;
//    private final UserAccountService userAccountService;


    @Override
    public void run(String... args) {
        // create company
        CompanyEntity company = CompanyEntity.builder()
                .name("Al Khawarizmi")
                .phone("123456789")
                .email("email@email.com")
                .address("agadir")
                .build();
        companyService.addCompany(
                company
        );

        // add category
        CategoryEntity category = CategoryEntity.builder()
                .name("categorie 1")
                .defaultAmount(199.0)
                .build();
        categoryService.addCategory(
                category
        );

        // create clients
        ClientEntity client = ClientEntity.builder()
                .name("naoufal jrhaider")
                .category(category)
                .company(company)
                .paymentIdentifier("MA99PO00")
                .email("email@email.com")
                .phone("123456789")
                .build();
        ClientEntity client1 = ClientEntity.builder()
                .name("oussama kritel")
                .category(category)
                .company(company)
                .paymentIdentifier("MA99PO01")
                .email("email@email.com")
                .phone("123456789")
                .build();
        clientService.addClient(client);
        clientService.addClient(client1);

        // create bills
        BillEntity bill1 = BillEntity.builder()
                .amount(200.2)
                .expiryDate(LocalDate.now().plusDays(1))
                .client(client)
                .company(company)
                .build();
        BillEntity bill2 = BillEntity.builder()
                .amount(200.2)
                .expiryDate(LocalDate.now().plusDays(5))
                .client(client)
                .company(company)
                .build();
        BillEntity bill3 = BillEntity.builder()
                .amount(200.2)
                .expiryDate(LocalDate.now().plusDays(2))
                .client(client1)
                .company(company)
                .build();
        BillEntity bill4 = BillEntity.builder()
                .amount(200.2)
                .expiryDate(LocalDate.now().plusDays(6))
                .client(client1)
                .company(company)
                .build();
        billService.addBill(bill1);
        billService.addBill(bill2);
        billService.addBill(bill3);
        billService.addBill(bill4);


    }
}
