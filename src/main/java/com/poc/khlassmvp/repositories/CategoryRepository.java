package com.poc.khlassmvp.repositories;

import com.poc.khlassmvp.entities.CategoryEntity;
import com.poc.khlassmvp.entities.CompanyEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
    List<CategoryEntity> findAllByCompanyId(Long companyId);
    boolean existsByName(String name);
}
