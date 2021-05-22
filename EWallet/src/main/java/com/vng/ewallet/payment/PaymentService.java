//package com.vng.ewallet.payment;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class PaymentService {
//    private final PaymentRepository paymentRepository;
//
//    @Autowired
//    public PaymentService(PaymentRepository paymentRepository) {
//        this.paymentRepository = paymentRepository;
//    }
//
//    public List<Payment> getPayments() {
//        return paymentRepository.findAll();
//    }
//
//    public ValidPayment addPayment(Payment payment) {
//        ValidPayment res = payment.isValid();
//        System.out.println(payment);
//        if(res == ValidPayment.SUCCESS) {
//            paymentRepository.save(payment);
//        }
//        return res;
//    }
//}
