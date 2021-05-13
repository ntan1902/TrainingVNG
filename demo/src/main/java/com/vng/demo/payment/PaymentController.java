package com.vng.demo.payment;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public List<Payment> getPayments() {
        return paymentService.getPayments();
    }

    @PostMapping
    public Map<String, String> addPayment(@RequestBody Payment payment) {
        Map<String, String> map = new HashMap<>();

        int res = paymentService.addPayment(payment);
        if(res == 0) {
            map.put("status", "200");
            map.put("message", "success");
        } else if(res == 1) {
            map.put("status", "400");
            map.put("error", "Bad Request");
            map.put("message", "Invalid bank name");
        } else if(res == 2) {
            map.put("status", "400");
            map.put("error", "Bad Request");
            map.put("message", "Invalid card number");
        }
        return map;
    }


}
