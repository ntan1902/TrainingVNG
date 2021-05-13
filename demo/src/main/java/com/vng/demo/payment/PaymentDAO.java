package com.vng.demo.payment;

import java.util.List;

public interface PaymentDAO {
    List<Payment> selectAll();
    int insertPayment(Payment payment);
}
