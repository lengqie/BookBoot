package cn.bookmanager.mapper;

import cn.bookmanager.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface PaymentMapper {

    /**
     * 充值
     * @param userId User.Id
     * @param recordId Record.Id
     * @param amount Amount
     * @param date Date
     * @return 操作的数量 成功则放回1
     */
    int pay(String userId, String recordId, Double amount, Date date);

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
