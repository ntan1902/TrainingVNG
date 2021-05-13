package com.vng.demo.payment;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("fakeDAO")
public class PaymentFakeDAO implements PaymentDAO {
    private static List<Payment> db = new ArrayList<>();

    @Override
    public List<Payment> selectAll(){
        return db;
    }

    @Override
    public int insertPayment(Payment payment) {
        payment.setId(db.size() + 1);
        db.add(payment);
        return 0;
    }
}
