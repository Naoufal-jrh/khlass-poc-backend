package com.poc.khlassmvp.services.impl;

import com.poc.khlassmvp.dtos.ReceiptDto;
import com.poc.khlassmvp.mapper.impl.ReceiptMapper;
import com.poc.khlassmvp.repositories.ReceiptRepository;
import com.poc.khlassmvp.services.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final ReceiptMapper receiptMapper;

    @Override
    public ReceiptDto getReceipt(Long id) {
        return receiptRepository.findById(id).map(receiptMapper::toDto).orElse(null);
    }

    @Override
    public List<ReceiptDto> getReceiptsByCompanyId(Long companyId) {
        return receiptRepository.findAllByBillCompanyId(companyId).stream().map(receiptMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ReceiptDto> getReceiptsByClientId(Long clientId) {
        return receiptRepository.findAllByBillClientId(clientId).stream().map(receiptMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ReceiptDto getReceiptByBillId(Long billId) {
        return receiptRepository.findById(billId).map(receiptMapper::toDto).orElse(null);
    }

    @Override
    public ReceiptDto addReceipt(ReceiptDto receiptDto) {
        return receiptMapper.toDto(receiptRepository.save(receiptMapper.toEntity(receiptDto)));
    }

    @Override
    public ReceiptDto updateReceipt(ReceiptDto receiptDto) {
        return receiptMapper.toDto(receiptRepository.save(receiptMapper.toEntity(receiptDto)));
    }

    @Override
    public void deleteReceipt(Long id) {
        receiptRepository.deleteById(id);
    }
}
