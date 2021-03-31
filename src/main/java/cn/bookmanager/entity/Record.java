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
    private String recordId;
    private Books books;
    private User user;
    private Date time;
    private int days;
    private int success;
}