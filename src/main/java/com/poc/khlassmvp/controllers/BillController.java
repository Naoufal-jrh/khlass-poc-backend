package com.poc.khlassmvp.controllers;

import com.poc.khlassmvp.dtos.BillDto;
import com.poc.khlassmvp.entities.ClientEntity;
import com.poc.khlassmvp.mapper.impl.BillMapper;
import com.poc.khlassmvp.services.BillService;
import com.poc.khlassmvp.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bill")
@RequiredArgsConstructor
public class BillController {
    private final BillService billService;
    private final BillMapper billMapper;

    @GetMapping
    public ResponseEntity<List<BillDto>> getBills(
            @RequestParam(required = false) Long clientId,
            @RequestParam(required = false) Long companyId,
            @RequestParam(required = false) Long categoryId
    ) {
        List<BillDto> bills;
        if (clientId != null) {
            bills = billService.getBillsByClientId(clientId);
        }
        else if (companyId != null) {
            bills = billService.getBillsByCompanyId(companyId);
        }
        else if (categoryId != null) {
            bills = billService.getBillsByCategoryId(categoryId);
        }
        else {
            bills = new ArrayList<>();
        }
        return ResponseEntity.ok(bills);
    }

    @GetMapping("/{billId}")
    public ResponseEntity<BillDto> getBillById(@PathVariable Long billId){
        return ResponseEntity.status(HttpStatus.FOUND).body(billService.getBillById(billId));
    }

    @PostMapping
    public ResponseEntity<List<BillDto>> createBill(@RequestBody List<BillDto> billDto){
        List<BillDto> bills = billService.addBills(billDto.stream().map(billMapper::toEntity).toList());
        return ResponseEntity.status(HttpStatus.CREATED).body(bills);
    }

    @PatchMapping("/{billId}")
    public ResponseEntity<BillDto> updateBill(@PathVariable Long billId, @RequestBody BillDto billDto) {
        BillDto bill =  billService.updateBill(billMapper.toEntity(billDto), billId);
        return ResponseEntity.created(URI.create("/bill/" + bill.getId())).body(bill);
    }

    @DeleteMapping("/{billId}")
    public ResponseEntity<Void> deleteBill(@PathVariable Long billId) {
        billService.deleteBillById(billId);
        return ResponseEntity.ok().build();
    }
}
