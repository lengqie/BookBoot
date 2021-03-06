package cn.bookmanager.service.Impl;

import cn.bookmanager.entity.Payment;
import cn.bookmanager.mapper.PaymentMapper;
import cn.bookmanager.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 支付记录的实现
 * @author lengqie
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentMapper paymentMapper;

    @Override
    public boolean pay(String recordId, Double amount, Date date) {
        return paymentMapper.pay(recordId,amount,date)==1;
    }

    @Override
    public Payment getPaymentById(long id) {
        return paymentMapper.getPaymentById(id);
    }

    @Override
    public List<Payment> getAllPayment() {
        return paymentMapper.getAllPayment();
    }
}
