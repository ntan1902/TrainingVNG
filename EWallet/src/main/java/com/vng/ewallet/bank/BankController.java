package com.vng.ewallet.bank;

import com.vng.ewallet.bank.factory.ValidBank;
import com.vng.ewallet.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bank")
public class BankController {
    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping
    public ResponseEntity<List<Bank>> findAllBanks() {
        List<Bank> banks = bankService.findAllBanks();
        return new ResponseEntity<>(banks, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Bank> insertBank(@RequestBody Bank bank){
        ValidBank res = bankService.insertBank(bank);
        if(res == ValidBank.INVALID_CODE) {
            throw new ApiRequestException("Invalid card number");
        } else if(res == ValidBank.INVALID_HOLDER_NAME) {
            throw new ApiRequestException("Invalid card holder name");
        }
        return new ResponseEntity<>(bank, HttpStatus.CREATED);
    }
}
