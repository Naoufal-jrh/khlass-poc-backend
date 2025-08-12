package com.poc.khlassmvp.services.impl;

import com.poc.khlassmvp.dtos.BillDto;
import com.poc.khlassmvp.entities.BillEntity;
import com.poc.khlassmvp.mapper.impl.BillMapper;
import com.poc.khlassmvp.repositories.BillRepository;
import com.poc.khlassmvp.repositories.ClientRepository;
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

    @Override
    public List<BillDto> getBillsByClientId(Long clientId) {
        return billRepository
                .findAllByClientId(clientId).stream().map(billMapper::toDto).toList();
    }

    @Override
    public List<BillDto> getBillsByCompanyId(Long companyId) {
        return billRepository.findAllByClientCategoryCompanyId(companyId).stream().map(billMapper::toDto).toList();
    }

    @Override
    public List<BillDto> getBillsByCategoryId(Long categoryId) {
        return billRepository.findAllByClientCategoryId(categoryId).stream().map(billMapper::toDto).toList();
    }

    // TODO : check if the bill with the same client - company - creation date exists already.
    @Override
    public BillDto addBill(BillEntity billEntity) {
        boolean exists = false;
        if (exists)
            throw new IllegalArgumentException("Bill with id " + billEntity.getId() + " already exists");
        else
            return billMapper.toDto(billRepository.save(billEntity));
    }

    // TODO : add a list of bills
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
            existingBill.setAmount(billEntity.getAmount());
        }
        if (billEntity.getClient() != null) {
            existingBill.setClient(billEntity.getClient());
        }
//        if (billEntity.getCompany() != null) {
//            existingBill.setCompany(billEntity.getCompany());
//        }
        if (billEntity.getPayed() != null) {
            existingBill.setPayed(billEntity.getPayed());
        }
        if (billEntity.getDescription() != null) {
            existingBill.setDescription(billEntity.getDescription());
        }
        if (billEntity.getExpiryDate() != null) {
            existingBill.setExpiryDate(billEntity.getExpiryDate());
        }

        return billMapper.toDto(billRepository.save(existingBill));
    }

    @Override
    public void deleteBillById(Long id) {
        BillEntity bill = billRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Bill not found with id " + id));

        billRepository.delete(bill);
    }


}
