package com.vng.ewallet.user;

import com.vng.ewallet.dto.BankUser;
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
@RequestMapping(path = "/api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> users = this.userService.findAllUsers();
        if(users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") Long id) {
        User user = this.userService.findUserById(id);
        if(user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> insertUser(@Valid @RequestBody User user, BindingResult result) {
        Map<String, String> err = Validate.checkValidate(result);

        if (err != null) {
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }

        User res = this.userService.insertUser(user);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @Valid @RequestBody User user,  BindingResult result) {
        Map<String, String> err = Validate.checkValidate(result);

        if (err != null) {
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }

        User res = this.userService.updateUser(id, user);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        if(this.userService.deleteUser(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{id}/banks")
    public ResponseEntity<List<BankUser>> findBanks(@PathVariable("id") Long id) {
        List<BankUser> bankUsers = this.userService.findBanks(id);
        if(bankUsers != null) {
            return new ResponseEntity<>(bankUsers, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
