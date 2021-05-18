package com.vng.demo.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentService {
    private final PaymentDAO paymentDAO;

    @Autowired
    public PaymentService(@Qualifier("fakeDAO") PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    public List<Payment> getPayments() {
        return paymentDAO.selectAll();
    }

    public ValidPayment addPayment(Payment payment) {
        ValidPayment res = payment.isValid();
        if(res == ValidPayment.SUCCESS) {
            paymentDAO.insertPayment(payment);
        }
        return res;
    }
}
