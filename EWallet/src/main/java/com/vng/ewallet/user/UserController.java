package com.vng.ewallet.user;

import com.vng.ewallet.bank.Bank;
import com.vng.ewallet.util.Validate;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/users")
@Log4j2
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {
        log.info("Inside findAllUsers of UserController");
        List<User> users = this.userService.findAllUsers();
        if (!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") Long id) {
        log.info("Inside findUserById of UserController");
        User user = this.userService.findUserById(id);
        if (user != null) {

            return ResponseEntity.ok().body(user);

        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> insertUser(@Valid @RequestBody User user,
                                        BindingResult result) {
        log.info("Inside insertUser of UserController");
        Map<String, String> err = Validate.checkValidate(result);

        if (err == null) {
            User res = this.userService.insertUser(user);
            return ResponseEntity.ok().body(res);
        }
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @Valid @RequestBody User user, BindingResult result) {
        log.info("Inside updateUser of UserController");
        Map<String, String> err = Validate.checkValidate(result);
        if (err == null) {
            User res = this.userService.updateUser(id, user);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        log.info("Inside deleteUser of UserController");
        if (this.userService.deleteUser(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}/banks")
    public ResponseEntity<List<Bank>> findAllBanks(@PathVariable("id") Long id) {
        log.info("Inside findAllBanks of UserController");
        List<Bank> banksUser = this.userService.findAllBanks(id);
        if (banksUser != null) {
            return new ResponseEntity<>(banksUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/link-bank")
    public ResponseEntity<User> linkBank(@PathVariable("id") Long id,
                                         @Valid @RequestBody Bank bank,
                                         BindingResult result) {
        log.info("Inside linkBank of UserController");
        Map<String, String> err = Validate.checkValidate(result);
        if (err == null) {
            User res = this.userService.linkBank(id, bank);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
