package com.poc.khlassmvp.controllers;

import com.poc.khlassmvp.dtos.BillDto;
import com.poc.khlassmvp.entities.ClientEntity;
import com.poc.khlassmvp.mapper.impl.BillMapper;
import com.poc.khlassmvp.services.BillService;
import com.poc.khlassmvp.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill")
@RequiredArgsConstructor
public class BillController {
    private final BillService billService;
    private final BillMapper billMapper;

    @GetMapping({"/company/{companyId}"})
    public List<BillDto> getAllBillsByCompanyId(@PathVariable Long companyId) {
        return billService.getBillsByCompanyId(companyId);
    }

    @GetMapping("/{billId}")
    public BillDto getBillById(@PathVariable Long billId) {
        return billService.getBillById(billId);
    }

    @GetMapping("/category/{categoryId}")
    public List<BillDto> getAllBillsByCategoryId(@PathVariable Long categoryId) {
        return billService.getBillsByCategoryId(categoryId);
    }

    @GetMapping("client/{clientId}")
    public List<BillDto> getAllBillsByClientId(@PathVariable Long clientId) {
        return billService.getBillsByClientId(clientId);
    }

    @PutMapping("/{billId}")
    public BillDto updateBill(@PathVariable Long billId, @RequestBody BillDto billDto) {
        return billService.updateBill(billMapper.toEntity(billDto), billId);
    }

    @DeleteMapping("/{billId}")
    public void deleteBill(@PathVariable Long billId) {
        billService.deleteBillById(billId);
    }


}
