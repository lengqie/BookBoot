package cn.bookmanager.service;

import cn.bookmanager.entity.Payment;

import java.util.Date;
import java.util.List;

public interface PaymentService {
    boolean pay(long id, String userId, String recordId, Double amount, Date date);

    Payment getPaymentById(long id);

    List<Payment> getAllPayment();
}
