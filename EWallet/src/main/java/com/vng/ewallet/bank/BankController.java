package com.vng.ewallet.bank;

import com.vng.ewallet.validation.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/bank")
public class BankController {
    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping
    public ResponseEntity<List<Bank>> findAllBanks() {
        List<Bank> banks = this.bankService.findAllBanks();
        if (banks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(banks, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> insertBank(@Valid @RequestBody Bank bank, BindingResult result) {
        Map<String, String> err = Validate.checkValidate(result);

        if (err != null) {
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }

        Bank response = this.bankService.insertBank(bank);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
