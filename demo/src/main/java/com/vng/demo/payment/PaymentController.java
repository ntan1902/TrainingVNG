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

        ValidPayment res = paymentService.addPayment(payment);
        if(res == ValidPayment.SUCCESS) {
            map.put("status", "200");
            map.put("message", "success");
        } else if(res == ValidPayment.INVALID_BANK_NAME) {
            map.put("status", "400");
            map.put("error", "Bad Request");
            map.put("message", "Invalid bank name");
        } else if(res == ValidPayment.INVALID_CARD_NUMBER) {
            map.put("status", "400");
            map.put("error", "Bad Request");
            map.put("message", "Invalid card number");
        } else if(res == ValidPayment.INVALID_CARD_HOLDER_NAME) {
            map.put("status", "400");
            map.put("error", "Bad Request");
            map.put("message", "Invalid card holder name");
        } else if(res == ValidPayment.INVALID_TYPE_IDENTITY) {
            map.put("status", "400");
            map.put("error", "Bad Request");
            map.put("message", "Invalid type identity");
        } else if(res == ValidPayment.INVALID_UID) {
            map.put("status", "400");
            map.put("error", "Bad Request");
            map.put("message", "Invalid uid");
        }
        return map;
    }


}
