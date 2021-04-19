package cn.bookmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author lengqie
 * 预定书籍记录
 * 未实现
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reserve {
    private int id;

    private String isbn;
    private String userId;


    private int success;

    private Date createTime;
    private Date modifiedTime;
}
