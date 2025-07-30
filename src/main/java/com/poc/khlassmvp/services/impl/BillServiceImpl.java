package com.poc.khlassmvp.services.impl;

import com.poc.khlassmvp.dtos.BillDto;
import com.poc.khlassmvp.entities.BillEntity;
import com.poc.khlassmvp.mapper.impl.BillMapper;
import com.poc.khlassmvp.repositories.BillRepository;
import com.poc.khlassmvp.services.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;
    private final BillMapper billMapper;

    @Override
    public BillDto addBill(BillEntity billEntity) {
        return billMapper.toDto(billRepository.save(billEntity));
    }

    @Override
    public List<BillDto> getBillsByClientId(Long clientId) {
        return billRepository.findAllByClientId(clientId).stream().map(billMapper::toDto).toList();
    }

    @Override
    public List<BillDto> getBillsByCompanyId(Long companyId) {
        return billRepository.findAllByCompanyId(companyId).stream().map(billMapper::toDto).toList();
    }

    @Override
    public List<BillDto> getBillsByCategoryId(Long categoryId) {
        return billRepository.findAllByClientCategoryId(categoryId).stream().map(billMapper::toDto).toList();
    }

    @Override
    public BillDto updateBill(BillEntity billEntity, Long billId) {
        billEntity.setId(billId);
        return billMapper.toDto(billRepository.save(billEntity));
    }

    @Override
    public void deleteBillById(Long id) {
        billRepository.deleteById(id);
    }

    @Override
    public BillDto getBillById(Long id) {
        return billRepository.findById(id).map(billMapper::toDto).orElse(null);
    }
}
