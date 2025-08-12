package com.poc.khlassmvp.repositories;

import com.poc.khlassmvp.entities.CategoryEntity;
import com.poc.khlassmvp.entities.ReceiptEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReceiptRepository extends CrudRepository<ReceiptEntity, Long> {
    ReceiptEntity findByBillId(Long billId);
    List<ReceiptEntity> findAllByBillClientCategoryCompanyId(Long companyId);
    List<ReceiptEntity> findAllByBillClientId(Long clientId);
}
