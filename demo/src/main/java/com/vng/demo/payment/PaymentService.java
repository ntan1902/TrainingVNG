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
//        return List.of(
//                new Payment(
//                        1,
//                        "VCB",
//                        "9704366614626076016",
//                        "NGUYEN TRINH AN",
//                        0,
//                        "026031189"
//                ),
//                new Payment(
//                        2,
//                        "AGB",
//                        "9704050700236339",
//                        "NGUYEN TRINH AN",
//                        0,
//                        "026031189"
//                )
//        );
    }

    public int addPayment(Payment payment) {
        int res = payment.isValid();
        if(res == 0) {
            return paymentDAO.insertPayment(payment);
        }

        return res;
    }
}
