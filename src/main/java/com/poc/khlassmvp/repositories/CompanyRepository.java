package com.poc.khlassmvp.repositories;

import com.poc.khlassmvp.entities.CategoryEntity;
import com.poc.khlassmvp.entities.CompanyEntity;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<CompanyEntity, Long> {
}
