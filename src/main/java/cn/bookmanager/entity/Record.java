package cn.bookmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author lengqie
 * 借阅记录
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    private String id;
    private Book book;
    private User user;
    private Date time;
    private int days;
    private int status;

    private Date createTime;
    private Date updateTime;
}