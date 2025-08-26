package com.poc.khlassmvp.services.impl;

import com.poc.khlassmvp.dtos.BillDto;
import com.poc.khlassmvp.entities.BillEntity;
import com.poc.khlassmvp.mapper.impl.BillMapper;
import com.poc.khlassmvp.repositories.BillRepository;
import com.poc.khlassmvp.services.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;
    private final BillMapper billMapper;

    @Override
    public BillDto getBillById(Long id) {
        return billRepository
                .findById(id)
                .map(billMapper::toDto)
                .orElseThrow(() -> new NoSuchElementException("Bill with id \" + id + \" not found"));
    }

    // TODO : should say if the client exist or not
    @Override
    public List<BillDto> getBillsByClientId(Long clientId) {
        return billRepository
                .findAllByClientId(clientId).stream().map(billMapper::toDto).toList();
    }

    // TODO : should say if the company exists or not
    @Override
    public List<BillDto> getBillsByCompanyId(Long companyId) {
        return billRepository.findAllByClientCategoryCompanyId(companyId).stream().map(billMapper::toDto).toList();
    }

    // TODO : should say if category exists or not
    @Override
    public List<BillDto> getBillsByCategoryId(Long categoryId) {
        return billRepository.findAllByClientCategoryId(categoryId).stream().map(billMapper::toDto).toList();
    }

    // TODO : check if the bill with the same client - company - creation date exists already.
    /*
     TODO : add business logic here :
     name : can not be empty, less than 50 words
     description : max 200 words, can be empty
     amount : should not be empty and should not be negative (can be zero)
     client : should not be null
     payed : is false by default, the value passed should be ignored
     expiry date : by default should be a month from generation, also the value passed should be ignored
     */
    @Override
    public BillDto addBill(BillEntity billEntity) {
        boolean exists = false;
        if (exists)
            throw new IllegalArgumentException("Bill with id " + billEntity.getId() + " already exists");
        else
            return billMapper.toDto(billRepository.save(billEntity));
    }

    @Override
    public List<BillDto> addBills(List<BillEntity> billEntities) {
        return billEntities.stream().map(this::addBill).collect(Collectors.toList());
    }

    @Override
    public BillDto updateBill(BillEntity billEntity, Long billId) {
        BillEntity existingBill = billRepository
                .findById(billId)
                .orElseThrow(() -> new NoSuchElementException("Bill not found with id " + billId));

        if (billEntity.getName() != null) {
            existingBill.setName(billEntity.getName());
        }
        if (billEntity.getAmount() != null) {
            // TODO : the amount should not be negative
            existingBill.setAmount(billEntity.getAmount());
        }
        if (billEntity.getClient() != null) {
            // TODO : see if you wanna give the option of changing the client of the bill
            existingBill.setClient(billEntity.getClient());
        }
        if (billEntity.getPayed() != null) {
            existingBill.setPayed(billEntity.getPayed());
        }
        if (billEntity.getDescription() != null) {
            // TODO : description should be 200 words maximum
            existingBill.setDescription(billEntity.getDescription());
        }
        if (billEntity.getExpiryDate() != null) {
            // TODO : expiry date should not be more than 1 year from now
            existingBill.setExpiryDate(billEntity.getExpiryDate());
        }

        return billMapper.toDto(billRepository.save(existingBill));
    }

    // TODO : only soft delete this, add a flag in the bill table that indicates if this is deleted or not.
    @Override
    public void deleteBillById(Long id) {

        BillEntity bill = billRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Bill not found with id " + id));
        billRepository.delete(bill);
    }


}
