package com.poc.khlassmvp.repositories;

import com.poc.khlassmvp.entities.CategoryEntity;
import com.poc.khlassmvp.entities.CompanyAccountEntity;
import org.springframework.data.repository.CrudRepository;

public interface CompanyAccountRepository extends CrudRepository<CompanyAccountEntity, Long> {
}
