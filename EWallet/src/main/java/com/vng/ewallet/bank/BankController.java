package com.vng.ewallet.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/banks")
public class BankController {
    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping
    public ResponseEntity<List<Bank>> findAllBanks() {
        List<Bank> banks = this.bankService.findAllBanks();
        if (!banks.isEmpty()) {
            return new ResponseEntity<>(banks, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bank> findBankById(@PathVariable("id") Long id) {
        Bank bank = this.bankService.findBankById(id);
        if(bank != null) {
            return new ResponseEntity<>(bank, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    //????
//    @PostMapping
//    public ResponseEntity<?> insertBank(@Valid @RequestBody Bank bank, BindingResult result) {
//        Map<String, String> err = Validate.checkValidate(result);
//
//        if (err == null) {
//            Bank response = this.bankService.insertBank(bank);
//            return new ResponseEntity<>(response, HttpStatus.CREATED);
//        }
//        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
//
//    }
//
//    @PatchMapping("/{id}")
//    public ResponseEntity<?> updateBank(@PathVariable("id") Long id, @Valid @RequestBody Bank bank,  BindingResult result) {
//        Map<String, String> err = Validate.checkValidate(result);
//
//        if (err == null) {
//            Bank res = this.bankService.updateBank(id, bank);
//            return new ResponseEntity<>(res, HttpStatus.OK);
//        }
//
//        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteBank(@PathVariable("id") Long id) {
//        if(this.bankService.deleteBank(id)) {
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
}
