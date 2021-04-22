package cn.bookmanager.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 逾期交易记录
 * @author lengqie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    private long id;
    private User user;
    private Record record;
    private BigDecimal amount;
    private Date createTime;
}
