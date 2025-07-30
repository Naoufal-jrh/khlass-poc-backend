package com.poc.khlassmvp.services;

import com.poc.khlassmvp.dtos.ReceiptDto;

import java.util.List;

public interface ReceiptService {
    ReceiptDto getReceipt(Long id);
    List<ReceiptDto> getReceiptsByCompanyId(Long companyId);
    List<ReceiptDto> getReceiptsByClientId(Long clientId);
    ReceiptDto getReceiptByBillId(Long billId);
    ReceiptDto addReceipt(ReceiptDto receiptDto);
    ReceiptDto updateReceipt(ReceiptDto receiptDto);
    void deleteReceipt(Long id);
}
