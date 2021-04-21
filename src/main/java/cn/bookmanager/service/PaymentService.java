package cn.bookmanager.service;

import cn.bookmanager.entity.Payment;

import java.util.Date;
import java.util.List;

public interface PaymentService {
    /**
     * 充值
     * @param userId User.Id
     * @param recordId Record.Id
     * @param amount Amount
     * @param date Date
     * @return 是否成功
     */
    boolean pay(String userId, String recordId, Double amount, Date date);

    /**
     * 获取一个交易记录
     * @param id pay.Id
     * @return
     */
    Payment getPaymentById(long id);

    /**
     * 获取全部的交易记录
     * @return
     */
    List<Payment> getAllPayment();
}
