package cn.bookmanager.mapper;

import cn.bookmanager.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface PaymentMapper {


    int pay(long id, String userId, String recordId, Double amount, Date date);

    Payment getPaymentById(long id);

    List<Payment> getAllPayment();
}
