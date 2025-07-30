package com.poc.khlassmvp.config;

import com.poc.khlassmvp.entities.BillEntity;
import com.poc.khlassmvp.entities.CategoryEntity;
import com.poc.khlassmvp.entities.ClientEntity;
import com.poc.khlassmvp.entities.CompanyEntity;
import com.poc.khlassmvp.mapper.impl.CategoryMapper;
import com.poc.khlassmvp.mapper.impl.ClientMapper;
import com.poc.khlassmvp.mapper.impl.CompanyMapper;
import com.poc.khlassmvp.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DbInitializer implements CommandLineRunner {
    private final CompanyService companyService;
    private final ClientService clientService;
    private final CategoryService categoryService;
    private final BillService billService;
    private final CompanyMapper companyMapper;
    private final CategoryMapper categoryMapper;
    private final ClientMapper clientMapper;

    @Override
    public void run(String... args) {
        List<CompanyEntity> companies = createCompanies(3);

        for (CompanyEntity company : companies) {
            List<CategoryEntity> categories = createCategories(company, 3);

            for (CategoryEntity category : categories) {
                int clientCount = (int) (4 + Math.random() * 3); // 4 to 6 clients
                List<ClientEntity> clients = createClients(company, category, clientCount);

                for (ClientEntity client : clients) {
                    createBills(company, client, 2 + (int) (Math.random() * 3)); // 2-4 bills per client
                }
            }
        }
    }

    // Helper to generate companies
    private List<CompanyEntity> createCompanies(int count) {
        List<CompanyEntity> companies = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            CompanyEntity company = CompanyEntity.builder()
                    .name("Company " + i)
                    .phone("10000000" + i)
                    .email("company" + i + "@test.com")
                    .address("Address " + i)
                    .build();
            companies.add(companyMapper.toEntity(companyService.addCompany(company)));
        }
        return companies;
    }

    // Helper to generate categories
    private List<CategoryEntity> createCategories(CompanyEntity company, int count) {
        List<CategoryEntity> categories = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            CategoryEntity category = CategoryEntity.builder()
                    .name(company.getName() + " - Category " + i)
                    .defaultAmount(100.0 * i)
                    .company(company)
                    .build();
            categories.add(categoryMapper.toEntity(categoryService.addCategory(category)));
        }
        return categories;
    }

    // Helper to generate clients
    private List<ClientEntity> createClients(CompanyEntity company, CategoryEntity category, int count) {
        List<ClientEntity> clients = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            ClientEntity client = ClientEntity.builder()
                    .name("Client " + i + " - " + company.getName())
                    .category(category)
                    .company(company)
                    .paymentIdentifier("PID-" + company.getName().charAt(company.getName().length() - 1) + "-" + category.getName().charAt(category.getName().length() - 1) + "-" + i)
                    .email("client" + i + "@" + company.getName().toLowerCase().replace(" ", "") + ".com")
                    .phone("1234567" + i)
                    .build();
            clients.add(clientMapper.toEntity(clientService.addClient(client)));
        }
        return clients;
    }

    // Helper to generate bills
    private void createBills(CompanyEntity company, ClientEntity client, int count) {
        for (int i = 1; i <= count; i++) {
            BillEntity bill = BillEntity.builder()
                    .amount(150.0 + (i * 10))
                    .expiryDate(LocalDate.now().plusDays(i * 2))
                    .client(client)
                    .company(company)
                    .build();
            billService.addBill(bill);
        }
    }
}