package com.poc.khlassmvp.repositories;

import com.poc.khlassmvp.entities.BillEntity;
import com.poc.khlassmvp.entities.CategoryEntity;
import com.poc.khlassmvp.entities.CompanyEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BillRepository extends CrudRepository<BillEntity, Long> {
    List<BillEntity> findAllByClientId(Long clientId);
    List<BillEntity> findAllByCompanyId(Long companyId);
    List<BillEntity>  findAllByClientCategoryId(Long clientCategoryId);
}
