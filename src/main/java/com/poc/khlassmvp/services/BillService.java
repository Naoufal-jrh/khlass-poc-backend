package com.poc.khlassmvp.services;

import com.poc.khlassmvp.dtos.BillDto;
import com.poc.khlassmvp.entities.BillEntity;

import java.util.List;

public interface BillService {
    BillDto addBill(BillEntity billEntity);
    List<BillDto> addBills(List<BillEntity> billEntities);
    List<BillDto> getBillsByClientId(Long clientId);
    List<BillDto> getBillsByCompanyId(Long companyId);
    List<BillDto> getBillsByCategoryId(Long categoryId);
    BillDto updateBill(BillEntity billEntity, Long billId);
    void deleteBillById(Long id);
    BillDto getBillById(Long id);
}
