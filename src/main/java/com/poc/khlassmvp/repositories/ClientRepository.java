package com.poc.khlassmvp.repositories;

import com.poc.khlassmvp.entities.CategoryEntity;
import com.poc.khlassmvp.entities.ClientEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<ClientEntity, Long> {
    List<ClientEntity> findAllByCategoryId(Long categoryId);

    List<ClientEntity> findAllByCategory_Company_Id(Long companyId);
}
